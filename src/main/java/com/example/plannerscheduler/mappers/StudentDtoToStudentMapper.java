package com.example.plannerscheduler.mappers;

import com.example.plannerscheduler.dto.StudentDtoRequest;
import com.example.plannerscheduler.dto.StudentDtoResponse;
import com.example.plannerscheduler.models.Student;
import org.mapstruct.Mapper;

@Mapper
public interface StudentDtoToStudentMapper {
    Student studentDtoToStudent(StudentDtoRequest studentDto);

    StudentDtoResponse studentToStudentDto(Student student);
}
