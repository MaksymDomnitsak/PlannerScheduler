package com.example.plannerscheduler.mappers;

import com.example.plannerscheduler.dto.SubjectDto;
import com.example.plannerscheduler.models.Subject;
import org.mapstruct.Mapper;

@Mapper
public interface SubjectDtoToSubjectMapper {
    Subject subjectDtoToSubject(SubjectDto subjectDto);

    SubjectDto subjectToSubjectDto(Subject subject);
}
