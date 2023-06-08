package com.example.plannerscheduler.controllers;

import com.example.plannerscheduler.dto.EventDto;
import com.example.plannerscheduler.models.Group;
import com.example.plannerscheduler.models.Schedule;
import com.example.plannerscheduler.models.Student;
import com.example.plannerscheduler.service.GroupService;
import com.example.plannerscheduler.service.ScheduleService;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.java6.auth.oauth2.VerificationCodeReceiver;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping(value="/api/toCalendar", produces = MediaType.APPLICATION_JSON_VALUE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class GoogleCalendarController {
    private static final String APPLICATION_NAME = "My First Project";

    @Value("${client.id}")
    private String CLIENT_ID = "";
    @Value("${client.secret}")
    private String CLIENT_SECRET = "";
    private static final String REDIRECT_URI = "http://localhost:8082/Callback";

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    GroupService groupService;

    ScheduleService scheduleService;

    ChromeOptions options;

    @Autowired
    public GoogleCalendarController(GroupService groupService,ScheduleService scheduleService) {
        this.groupService=groupService;
        this.scheduleService=scheduleService;
        System.setProperty("webdriver.chrome.driver", "/chromedriver.exe");
        this.options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> importEvent(@RequestBody EventDto eventForm) throws IOException, GeneralSecurityException {

        HttpTransport httpTransport = new NetHttpTransport();
        WebDriver driver = new ChromeDriver(options);
        Credential credential = authorize(driver);


        Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();

        driver.close();

        Schedule fromDB = scheduleService.getById(eventForm.getScheduleId());
        Event event = new Event();
        event.setSummary(eventForm.getSummary());
        event.setLocation(eventForm.getLocation());
        event.setDescription(eventForm.getDescription());

        DateTime startDateTime =new DateTime(eventForm.getStartDate()+"T"+fromDB.getStartTime().minusHours(3).toString()+":00");
        DateTime endDateTime = new DateTime(eventForm.getStartDate()+"T"+fromDB.getEndTime().minusHours(3).toString()+":00");
        event.setStart(new EventDateTime().setDateTime(startDateTime).setTimeZone("Europe/Kiev"));
        event.setEnd(new EventDateTime().setDateTime(endDateTime).setTimeZone("Europe/Kiev"));

        if (eventForm.getFrequency().equals("weekly")) {
            event.setRecurrence(Collections.singletonList("RRULE:FREQ=WEEKLY;COUNT=" + eventForm.getRepeats()));
        } else if (eventForm.getFrequency().equals("two-weeks")) {
            event.setRecurrence(Collections.singletonList("RRULE:FREQ=WEEKLY;INTERVAL=2;COUNT=" + eventForm.getRepeats()));
        }
        List<EventAttendee> allAttendees = new ArrayList<>();
        if(eventForm.getGroupsId().length!=0) {
            event.setSummary(event.getSummary() + " (");
            for (Long id : eventForm.getGroupsId()) {
                Group group = groupService.getById(id);
                event.setSummary(event.getSummary()+group.getName()+", ");
                for (Student student : group.getStudents()) {
                    allAttendees.add(new EventAttendee().setEmail(student.getEmail()));
                }
            }
            event.setSummary(event.getSummary().substring(0,event.getSummary().length()-2)+")");
        }
        for(String email: eventForm.getAttendeesEmails()){
            if(!allAttendees.contains(new EventAttendee().setEmail(email))){
                allAttendees.add(new EventAttendee().setEmail(email));
            }
        }
        event.setAttendees(allAttendees);

        ConferenceData conferenceData = new ConferenceData();
        ConferenceSolutionKey conferenceSolution = new ConferenceSolutionKey();
        if (eventForm.getConference().equals("meet")) {
            conferenceSolution.set("key","hangoutsMeet");

        } else if (eventForm.getConference().equals("hangout")) {
            conferenceSolution.setType("eventHangout");

        }
        CreateConferenceRequest createConferenceRequest = new CreateConferenceRequest();
        createConferenceRequest.setRequestId(UUID.randomUUID().toString());
        createConferenceRequest.setConferenceSolutionKey(conferenceSolution);
        conferenceData.setCreateRequest(createConferenceRequest);
        event.setConferenceData(conferenceData);

        service.events().insert("primary", event).execute();
        return ResponseEntity.ok("Ok");
    }

    public Credential authorize(WebDriver driver) throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = new NetHttpTransport();

        GoogleClientSecrets clientSecrets = new GoogleClientSecrets()
                .setWeb(new GoogleClientSecrets.Details()
                        .setClientId(CLIENT_ID)
                        .setClientSecret(CLIENT_SECRET)
                        .setRedirectUris(Collections.singletonList(REDIRECT_URI)));

        AuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        httpTransport,
                        JSON_FACTORY,
                        clientSecrets,
                        Collections.singletonList(CalendarScopes.CALENDAR))
                        .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                        .setAccessType("offline")
                        .build();

        VerificationCodeReceiver receiver = new LocalServerReceiver.Builder().setPort(8082).build();

        String url = flow.getAuthorizationServerEncodedUrl()+"?"+
                "access_type=offline&client_id="+CLIENT_ID+
                "&redirect_uri="+REDIRECT_URI+"&response_type=code&scope=https://www.googleapis.com/auth/calendar";

        driver.get(url);

        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

    }
}
