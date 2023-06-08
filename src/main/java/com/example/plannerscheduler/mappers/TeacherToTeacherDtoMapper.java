package com.example.plannerscheduler.mappers;

import com.example.plannerscheduler.dto.TeacherDtoRequest;
import com.example.plannerscheduler.dto.TeacherDtoResponse;
import com.example.plannerscheduler.models.Teacher;
import org.mapstruct.Mapper;

@Mapper
public interface TeacherToTeacherDtoMapper {

    Teacher teacherDtoToTeacher(TeacherDtoRequest teacherDto);
     TeacherDtoResponse teacherToTeacherDto(Teacher teacher);
}
