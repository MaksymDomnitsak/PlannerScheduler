package com.example.plannerscheduler.mappers.impl;

import com.example.plannerscheduler.dto.CustomScheduleDtoRequest;
import com.example.plannerscheduler.dto.ScheduleDtoRequest;
import com.example.plannerscheduler.models.Schedule;
import com.example.plannerscheduler.models.User;
import com.example.plannerscheduler.service.GroupService;
import com.example.plannerscheduler.service.SubjectService;
import com.example.plannerscheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleDtoToSchedule {

    UserService userService;

    GroupService groupService;

    SubjectService subjectService;

    @Autowired
    public ScheduleDtoToSchedule(UserService userService, GroupService groupService, SubjectService subjectService) {
        this.userService = userService;
        this.groupService = groupService;
        this.subjectService = subjectService;
    }


    public Schedule scheduleDtoToSchedule(ScheduleDtoRequest scheduleDto) {
        if ( scheduleDto == null ) {
            return null;
        }

        Schedule schedule = new Schedule();
        schedule.setSubject(subjectService.getById(scheduleDto.getSubjectId()));
        schedule.setGroup(groupService.getById(scheduleDto.getGroupId()));
        schedule.setCreator(userService.getById(scheduleDto.getUserCreatorId()));
        schedule.setDayOfWeek( scheduleDto.getDayOfWeek() );
        schedule.setEvenWeek( scheduleDto.getEvenWeek() );
        schedule.setLessonOrder( scheduleDto.getLessonOrder() );
        schedule.setTypeOfLesson( scheduleDto.getTypeOfLesson() );
        schedule.setOnline( scheduleDto.getOnline() );
        schedule.setAuditoryNumber( scheduleDto.getAuditoryNumber() );
        schedule.setCustomTitle(scheduleDto.getCustomTitle());

        return schedule;
    }

    public Schedule customScheduleDtoToSchedule(CustomScheduleDtoRequest scheduleDto) {
        if ( scheduleDto == null ) {
            return null;
        }
        Schedule schedule = new Schedule();
        if(!scheduleDto.getAttendees().isEmpty()) {
            List<User> userList = new ArrayList<>();
            for(int i=0;i<scheduleDto.getAttendees().size();i++){
                userList.add(userService.getById(scheduleDto.getAttendees().get(i)));
            }
            schedule.setAttendees(userList);
        }
        schedule.setSubject(subjectService.getById(scheduleDto.getSubjectId()));
        schedule.setGroup(groupService.getById(scheduleDto.getGroupId()));
        schedule.setCreator(userService.getById(scheduleDto.getCreatorId()));

        schedule.setDayOfWeek( scheduleDto.getDayOfWeek() );
        schedule.setEvenWeek( scheduleDto.getEvenWeek() );
        schedule.setLessonOrder( scheduleDto.getLessonOrder() );
        schedule.setTypeOfLesson( scheduleDto.getTypeOfLesson() );
        schedule.setStartTime(scheduleDto.getStartTime());
        schedule.setEndTime(scheduleDto.getEndTime());
        schedule.setOnline( scheduleDto.getOnline() );
        schedule.setAuditoryNumber( scheduleDto.getAuditoryNumber() );
        schedule.setCustomTitle(scheduleDto.getCustomTitle());

        return schedule;
    }
}
