package com.example.plannerscheduler.mappers;

import com.example.plannerscheduler.dto.NoteDtoRequest;
import com.example.plannerscheduler.dto.NoteDtoResponse;
import com.example.plannerscheduler.models.Note;
import com.example.plannerscheduler.service.ScheduleService;
import com.example.plannerscheduler.service.StudentService;
import com.example.plannerscheduler.service.interfaces.ScheduleServiceInterface;
import com.example.plannerscheduler.service.interfaces.StudentServiceInterface;
import com.example.plannerscheduler.service.interfaces.UserServiceInterface;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NoteDtoToNoteMapper {

    NoteDtoResponse noteToDtoNote(Note note);
    
}
