package com.example.plannerscheduler.controllers;

import com.example.plannerscheduler.dto.CustomScheduleDtoRequest;
import com.example.plannerscheduler.dto.CustomScheduleDtoResponse;
import com.example.plannerscheduler.dto.ScheduleDtoRequest;
import com.example.plannerscheduler.dto.ScheduleDtoResponse;
import com.example.plannerscheduler.mappers.ScheduleDtoToScheduleMapper;
import com.example.plannerscheduler.mappers.impl.ScheduleDtoToSchedule;
import com.example.plannerscheduler.models.Schedule;
import com.example.plannerscheduler.service.ScheduleService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.Page.empty;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping(value="/api/schedule", produces = MediaType.APPLICATION_JSON_VALUE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ScheduleController {

    ScheduleService scheduleService;

    private ScheduleDtoToScheduleMapper mapper = Mappers.getMapper(ScheduleDtoToScheduleMapper.class);

    ScheduleDtoToSchedule toObjectMapper;


    public ScheduleController(ScheduleService scheduleService, ScheduleDtoToSchedule toObjectMapper) {
        this.scheduleService = scheduleService;
        this.toObjectMapper = toObjectMapper;
    }



    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ScheduleDtoResponse>> getAllSorted() {
        List<ScheduleDtoResponse> scheduleList = new ArrayList<>();
        scheduleService.getAll().forEach(schedule -> scheduleList.add(mapper.scheduleToDtoSchedule(schedule)));
        return ResponseEntity.ok(scheduleList);
    }

    @GetMapping(params = {"groupId"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ScheduleDtoResponse>> getAllByGroupId(@RequestParam("groupId") String groupId){
        List<ScheduleDtoResponse> scheduleList = new ArrayList<>();
        scheduleService.getByGroupId(groupId).forEach(schedule -> scheduleList.add(mapper.scheduleToDtoSchedule(schedule)));
        return ResponseEntity.ok(scheduleList);
    }
    @GetMapping(params = {"teacherId"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ScheduleDtoResponse>> getAllByTeacherId(@RequestParam("teacherId") Long teacherId){
        List<ScheduleDtoResponse> scheduleList = new ArrayList<>();
        scheduleService.getByTeacherId(teacherId)
                .forEach(schedule -> scheduleList.add(mapper.scheduleToDtoSchedule(schedule)));
        return ResponseEntity.ok(scheduleList);
    }
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CustomScheduleDtoResponse>> getAll() {
        List<CustomScheduleDtoResponse> scheduleList = new ArrayList<>();
        scheduleService.findAll().forEach(schedule -> scheduleList.add(mapper.scheduleToCustomDtoSchedule(schedule)));
        return ResponseEntity.ok(scheduleList);
    }

    @GetMapping(value="/time",params = {"creatorId"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CustomScheduleDtoResponse>> getAllWithTimeByTeacherId(@RequestParam("creatorId") Long creatorId){
        List<CustomScheduleDtoResponse> scheduleList = new ArrayList<>();
        scheduleService.getWithTimeByCreatorId(creatorId)
                .forEach(schedule -> {
                    if (!scheduleService.contains(scheduleList, schedule)) {
                        scheduleList.add(mapper.scheduleToCustomDtoSchedule(schedule));
                    }
                });
        return ResponseEntity.ok(scheduleList);
    }

    @GetMapping(value="/groupTime",params = {"groupId"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CustomScheduleDtoResponse>> getAllWithTimeByGroupId(@RequestParam("groupId") String groupId){
        List<CustomScheduleDtoResponse> scheduleList = new ArrayList<>();
        scheduleService.getWithTimeByGroupId(groupId)
                .forEach(schedule -> {
                    if (!scheduleService.contains(scheduleList, schedule)) {
                        scheduleList.add(mapper.scheduleToCustomDtoSchedule(schedule));
                    }
                });
        return ResponseEntity.ok(scheduleList);
    }

    @GetMapping(params = {"attendeesEmail"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CustomScheduleDtoResponse>> getAllWithTimeByTeacherId(@RequestParam("attendeesEmail") String attendeesEmail){
        List<CustomScheduleDtoResponse> scheduleList = new ArrayList<>();
        scheduleService.getByEmail(attendeesEmail).forEach(schedule -> scheduleList.add(mapper.scheduleToCustomDtoSchedule(schedule)));
        return ResponseEntity.ok(scheduleList);
    }

    @GetMapping(params = {"page","size"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<ScheduleDtoResponse>> getPaginated(@RequestParam("page") int page , @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page,size);
        List<ScheduleDtoResponse> scheduleList = new ArrayList<>();
        Page<Schedule> events = scheduleService.getAll(pageable);
        events.forEach(schedule -> scheduleList.add(mapper.scheduleToDtoSchedule(schedule)));
        Page<ScheduleDtoResponse> pageEvents = new PageImpl<>(scheduleList,pageable,events.getTotalElements());
        return ResponseEntity.ok(pageEvents);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CustomScheduleDtoResponse> getScheduleById(@PathVariable("id") Long id){
        CustomScheduleDtoResponse response = mapper.scheduleToCustomDtoSchedule(scheduleService.getById(id));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ScheduleDtoResponse createSchedule(@RequestBody ScheduleDtoRequest schedule){
        return mapper.scheduleToDtoSchedule(scheduleService.createSchedule(toObjectMapper.scheduleDtoToSchedule(schedule)));
    }

    @PostMapping("/custom")
    @ResponseStatus(HttpStatus.OK)
    public CustomScheduleDtoResponse createCustomSchedule(@RequestBody CustomScheduleDtoRequest schedule){
        return mapper.scheduleToCustomDtoSchedule(scheduleService.createSchedule(toObjectMapper.customScheduleDtoToSchedule(schedule)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("#scheduleService.getById(id).creator.id == authentication.principal.id or hasAnyAuthority('ADMIN')")
    public void deleteSchedule(@PathVariable("id") Long id)
    {
        scheduleService.delete(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("#schedule.userCreatorId == authentication.principal.id or hasAnyAuthority('ADMIN')")
    public CustomScheduleDtoResponse updateSchedule(@PathVariable("id") Long id,
                                      @RequestBody CustomScheduleDtoRequest schedule){
        return mapper.scheduleToCustomDtoSchedule(scheduleService.updateSchedule(id, toObjectMapper.customScheduleDtoToSchedule(schedule)));
    }



}
