package com.example.plannerscheduler.mappers;

import com.example.plannerscheduler.dto.SubjectDtoRequest;
import com.example.plannerscheduler.dto.SubjectDtoResponse;
import com.example.plannerscheduler.models.Subject;
import org.mapstruct.Mapper;

@Mapper
public interface SubjectDtoToSubjectMapper {
    Subject subjectDtoToSubject(SubjectDtoRequest subjectDto);

    SubjectDtoResponse subjectToSubjectDto(Subject subject);
}
