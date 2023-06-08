package com.example.plannerscheduler.mappers.impl;

import com.example.plannerscheduler.dto.NoteDtoRequest;
import com.example.plannerscheduler.models.Note;
import com.example.plannerscheduler.service.ScheduleService;
import com.example.plannerscheduler.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoteDtoToNote {

    ScheduleService scheduleService;

    StudentService studentService;

    @Autowired
    public NoteDtoToNote(ScheduleService scheduleService, StudentService studentService) {
        this.scheduleService = scheduleService;
        this.studentService = studentService;
    }

    public Note noteDtoToNote(NoteDtoRequest noteDto) {
        if ( noteDto == null ) {
            return null;
        }

        Note note = new Note();

        note.setTitle( noteDto.getTitle() );
        note.setLesson(scheduleService.getById(noteDto.getLesson_id()));
        note.setStudent(studentService.getById(noteDto.getStudent_id()));
        note.setBody( noteDto.getBody() );
        note.setFinished( noteDto.getFinished() );

        return note;
    }
}
