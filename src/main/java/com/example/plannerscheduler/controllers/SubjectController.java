package com.example.plannerscheduler.controllers;

import com.example.plannerscheduler.dto.SubjectDto;
import com.example.plannerscheduler.mappers.SubjectDtoToSubjectMapper;
import com.example.plannerscheduler.service.SubjectService;;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/subject", produces = MediaType.APPLICATION_JSON_VALUE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SubjectController {

    SubjectService subjectService;

    private SubjectDtoToSubjectMapper mapper = Mappers.getMapper(SubjectDtoToSubjectMapper.class);


    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<SubjectDto>> getAll() {
        List<SubjectDto> subjectList = new ArrayList<>();
        subjectService.getAll().forEach(subject -> subjectList.add(mapper.subjectToSubjectDto(subject)));
        return ResponseEntity.ok(subjectList);
    }

    @GetMapping(params = {"page","size"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<SubjectDto>> getPaginated(@RequestParam("page") int page , @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page,size);
        List<SubjectDto> subjectList = new ArrayList<>();
        subjectService.getAll(pageable).forEach(subject -> subjectList.add(mapper.subjectToSubjectDto(subject)));
        return ResponseEntity.ok(subjectList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable("id") Long id){
        SubjectDto response = mapper.subjectToSubjectDto(subjectService.getById(id));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SubjectDto createSubject(@RequestBody SubjectDto subject){
        return mapper.subjectToSubjectDto(subjectService.createSubject(mapper.subjectDtoToSubject(subject)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void deleteSubject(@PathVariable("id") Long id)
    {
        subjectService.delete(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SubjectDto updateSubject(@PathVariable("id") Long id,
                                      @RequestBody SubjectDto subject){
        return mapper.subjectToSubjectDto(subjectService.updateSubject(id, mapper.subjectDtoToSubject(subject)));
    }

}
