package com.example.plannerscheduler.service;

import com.example.plannerscheduler.dto.CustomScheduleDtoResponse;
import com.example.plannerscheduler.dto.ScheduleDtoResponse;
import com.example.plannerscheduler.enums.LessonType;
import com.example.plannerscheduler.exception.ObjectNotFoundException;
import com.example.plannerscheduler.models.Group;
import com.example.plannerscheduler.models.Schedule;
import com.example.plannerscheduler.repository.ScheduleRepository;
import com.example.plannerscheduler.service.interfaces.ScheduleServiceInterface;
import jakarta.transaction.Transactional;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
@Service
public class ScheduleService implements ScheduleServiceInterface {
    ScheduleRepository scheduleRepository;


    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    public List<Schedule> getAll() {
        List<Schedule> schedules = scheduleRepository.getAllSchedule();
        return schedules;
    }

    public Page<Schedule> getAll(Pageable pageable)
    {
        Page<Schedule> page = scheduleRepository.findPageOfSchedule(pageable);
        return page;
    }

    public Schedule getById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Entity was not found  ID: ", id));
    }

    public List<Schedule> getByGroupId(String groupId){
        return scheduleRepository.findAllByGroupIdOrderByDayOfWeekLessonOrder(groupId);
    }

    public List<Schedule> getByTeacherId(Long teacherId){
        return scheduleRepository.findAllByTeacherIdOrderByDayOfWeekLessonOrder(teacherId);
    }

    public List<Schedule> getWithTimeByCreatorId(Long creatorId){
        return scheduleRepository.findAllByCreatorIdOrdered(creatorId);
    }

    public List<Schedule> getWithTimeByGroupId(String groupId){
        return scheduleRepository.findAllByGroupIdOrdered(groupId);
    }

    public List<Schedule> getByEmail(String attendeesEmail){
        return scheduleRepository.findAllByAttendeesEmailOrdered(attendeesEmail);
    }


    @Transactional
    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }

    @Transactional
    public Schedule createSchedule(Schedule newSchedule) {
        return scheduleRepository.save(newSchedule);
    }

    @Transactional
    public Schedule updateSchedule(Long id, Schedule newSchedule){
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(RuntimeException::new);
        newSchedule.setId(id);
        //newSchedule.setCreatedAt(schedule.getCreatedAt());
        return scheduleRepository.save(newSchedule);

    }

     public boolean contains(List<CustomScheduleDtoResponse> scheduleList, Schedule event){
        if(event.getTypeOfLesson().name().equals("CUSTOM") || event.getTypeOfLesson().name().equals("EXAM") ||
                event.getTypeOfLesson().name().equals("TEST")){
            return false;
        }
        for(CustomScheduleDtoResponse schedule: scheduleList){
            if(event.getLessonOrder() == schedule.getLessonOrder() && event.getSubject().getName() == schedule.getSubject().getName()
            && event.getGroup().getName() == schedule.getGroup().getName() && event.getDayOfWeek() == schedule.getDayOfWeek() &&
            event.getTypeOfLesson() == schedule.getTypeOfLesson()){
                return true;
            }
        }
        return false;
    }
}

