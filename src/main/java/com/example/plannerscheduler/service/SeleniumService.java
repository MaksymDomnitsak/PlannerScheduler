package com.example.plannerscheduler.service;

import com.example.plannerscheduler.enums.LessonType;
import com.example.plannerscheduler.enums.UserRole;
import com.example.plannerscheduler.models.Group;
import com.example.plannerscheduler.models.Schedule;
import com.example.plannerscheduler.models.Subject;
import com.example.plannerscheduler.models.Teacher;
import com.example.plannerscheduler.repository.GroupRepository;
import com.example.plannerscheduler.repository.ScheduleRepository;
import com.example.plannerscheduler.repository.SubjectRepository;
import com.example.plannerscheduler.repository.TeacherRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
@Service
public class SeleniumService {
    WebDriver driver;
    ChromeOptions options;
    GroupRepository groupRepository;

    TeacherRepository teacherRepository;

    SubjectRepository subjectRepository;

    ScheduleRepository scheduleRepository;

    @Autowired
    public SeleniumService(GroupRepository groupRepository, TeacherRepository teacherRepository, SubjectRepository subjectRepository, ScheduleRepository scheduleRepository) {
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
        this.scheduleRepository = scheduleRepository;
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        this.options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
    }

    private void setupDriver(){
        this.driver = new ChromeDriver(options);
    }

    public List<Group> loadGroups() {

        setupDriver();
        driver.get("http://fmi-schedule.chnu.edu.ua/schedule?semester=55");

        WebElement table = new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));

        WebElement thead = table.findElement(By.className("MuiTableHead-root"));
        WebElement row = thead.findElement(By.tagName("tr"));
        List<WebElement> cells = row.findElements(By.tagName("th"));

        for (int i=1;i<cells.size();i++) {
            String groupNumber = cells.get(i).getText().trim();
            if(!groupRepository.existsGroupByName(groupNumber)) {
                Group group = new Group(groupNumber);
                groupRepository.save(group);
            }
        }
        driver.quit();

        return groupRepository.findAll();
    }

    public void getFromSchedule() {

        setupDriver();
        int i=1;
        boolean pairing=true;
        Long dayIt=0L,orderIt=0L; // те що і у наступному коментарі з групою
        Long groupIt = 1L;
        List<Group> groups = groupRepository.findAll(); //починати з 1 елемента бо 0 - пробна група

        driver.get("http://fmi-schedule.chnu.edu.ua/schedule?semester=55");

        WebElement table = new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));

        WebElement tbody = table.findElement(By.className("MuiTableBody-root"));
        List<WebElement> rows = tbody.findElements(By.tagName("tr"));
        for (int j=0;j<rows.size();j++) {
            List<WebElement> cells = rows.get(j).findElements(By.tagName("td"));
            if(j%10==0){
                dayIt++;
                orderIt=0L;
                i=3;
            }
            if(j%2==0){
                if(orderIt!=0) i=2;
                orderIt++;
            }
            if(pairing==false){
                pairing = true;
                i=1;
            }else pairing = false;
            groupIt=1L; //починати з 1 елемента бо 0 - пробна група
            for (; i < cells.size(); i++) {
                if(cells.get(i).getText().trim() != "") {
                    String attr = cells.get(i).getAttribute("colspan");
                    if(attr!=null){
                        for(int groupedCell=0;groupedCell<Integer.parseInt(attr);groupedCell++){
                            //створювати пару, предмет, вчителя
                            Long subjectId = 0L, teacherId = 0L;
                            String body = cells.get(i).getText();
                            body = body.substring(body.indexOf(" "));
                            String name = body.substring(0, body.indexOf("\n")).trim();
                            String firstName = name.substring(0, name.indexOf(" ")).trim();
                            String patronymic = name.substring(name.lastIndexOf(" ")).trim();
                            String lastName = name.substring(name.indexOf(" "),name.lastIndexOf(" ")).trim();
                            body = body.substring(body.indexOf("\n")+1);
                            String subject = body.substring(0, body.indexOf("\n")).trim();
                            body = body.substring(body.indexOf("\n")+1);
                            String typeLesson = body.substring(1, body.indexOf(",")).trim().toUpperCase();
                            body = body.substring(body.indexOf(",")+1);
                            String auditoryOnline = body.substring(1, body.indexOf(")")).trim();
                            if(!subjectRepository.existsSubjectByName(subject)) {
                                subjectId = subjectRepository.save(new Subject(subject)).getId();
                            }else subjectId = subjectRepository.findByName(subject).getId();
                            if(!teacherRepository.existsTeacherByFirstNameAndLastNameAndPatronymicName(firstName,lastName,patronymic)){
                                teacherId = teacherRepository.save(new Teacher(firstName,lastName,patronymic,null,null, UserRole.TEACHER)).getId();
                            }else teacherId = teacherRepository.findTeacherByFirstNameAndLastNameAndPatronymicName(firstName,lastName,patronymic).getId();
                            if(auditoryOnline.contains("онлайн")) {
                                if (!scheduleRepository.existsBySubjectAndTeacherAndGroupAndDayOfWeekAndLessonOrderAndEvenWeekAndTypeOfLesson(subjectId, teacherId, (groupIt + 1), dayIt, orderIt, pairing, LessonType.valueOf(typeLesson))) {
                                    scheduleRepository.save(new Schedule(subjectRepository.findById(subjectId).get(), teacherRepository.findById(teacherId).get(),
                                            groups.get(groupIt.intValue()), dayIt, pairing, orderIt, LessonType.valueOf(typeLesson), true, null,
                                            orderStartLessonParse(orderIt), orderEndLessonParse(orderIt)));
                                } else {
                                    Schedule sch = scheduleRepository.findScheduleByDayOfWeekAndLessonOrderAndEvenWeekAndGroup(dayIt, orderIt, pairing, groups.get(groupIt.intValue()).getId());
                                    scheduleRepository.save(new Schedule(sch.getId(), subjectRepository.findById(subjectId).get(), teacherRepository.findById(teacherId).get(),
                                            groups.get(groupIt.intValue()), dayIt, pairing, orderIt, LessonType.valueOf(typeLesson), true, null,
                                            orderStartLessonParse(orderIt), orderEndLessonParse(orderIt)));
                                }
                            } else {
                                if (!scheduleRepository.existsBySubjectAndTeacherAndGroupAndDayOfWeekAndLessonOrderAndEvenWeekAndTypeOfLesson(subjectId, teacherId, (groupIt + 1), dayIt, orderIt, pairing, LessonType.valueOf(typeLesson))) {
                                    scheduleRepository.save(new Schedule(subjectRepository.findById(subjectId).get(), teacherRepository.findById(teacherId).get(),
                                            groups.get(groupIt.intValue()), dayIt, pairing, orderIt, LessonType.valueOf(typeLesson), false, auditoryOnline,
                                            orderStartLessonParse(orderIt), orderEndLessonParse(orderIt)));
                                } else {
                                    Schedule sch = scheduleRepository.findScheduleByDayOfWeekAndLessonOrderAndEvenWeekAndGroup(dayIt, orderIt, pairing, groups.get(groupIt.intValue()).getId());
                                    scheduleRepository.save(new Schedule(sch.getId(), subjectRepository.findById(subjectId).get(), teacherRepository.findById(teacherId).get(),
                                            groups.get(groupIt.intValue()), dayIt, pairing, orderIt, LessonType.valueOf(typeLesson), false, auditoryOnline,
                                            orderStartLessonParse(orderIt), orderEndLessonParse(orderIt)));//може видаляти стару пару!
                                }
                            }
                            groupIt++;
                        }
                    }
                }else groupIt++;

            }
        }
        driver.quit();

    }

    private LocalTime orderStartLessonParse(Long orderIt){
        return switch (orderIt.intValue()) {
            case 1 -> LocalTime.parse("08:20");
            case 2 -> LocalTime.parse("09:50");
            case 3 -> LocalTime.parse("11:30");
            case 4 -> LocalTime.parse("13:00");
            case 5 -> LocalTime.parse("14:40");
            default -> LocalTime.of(0, 0);
        };
    }

    private LocalTime orderEndLessonParse(Long orderIt){
        return switch (orderIt.intValue()) {
            case 1 -> LocalTime.parse("09:40");
            case 2 -> LocalTime.parse("11:10");
            case 3 -> LocalTime.parse("12:50");
            case 4 -> LocalTime.parse("14:20");
            case 5 -> LocalTime.parse("16:00");
            default -> LocalTime.of(0, 0);
        };
    }
}
