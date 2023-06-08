package com.example.plannerscheduler.controllers;

import com.example.plannerscheduler.dto.TeacherDtoRequest;
import com.example.plannerscheduler.dto.TeacherDtoResponse;
import com.example.plannerscheduler.mappers.TeacherToTeacherDtoMapper;
import com.example.plannerscheduler.service.TeacherService;
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
@RequestMapping(value="/api/teacher", produces = MediaType.APPLICATION_JSON_VALUE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TeacherController {
    TeacherService teacherService;

    private TeacherToTeacherDtoMapper mapper = Mappers.getMapper(TeacherToTeacherDtoMapper.class);


    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TeacherDtoResponse>> getAll() {
        List<TeacherDtoResponse> teacherList = new ArrayList<>();
        teacherService.getAll().forEach(teacher -> teacherList.add(mapper.teacherToTeacherDto(teacher)));
        return ResponseEntity.ok(teacherList);
    }

    @GetMapping(params = {"page","size"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TeacherDtoResponse>> getPaginated(@RequestParam("page") int page , @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page,size);
        List<TeacherDtoResponse> teacherList = new ArrayList<>();
        teacherService.getAll(pageable).forEach(teacher -> teacherList.add(mapper.teacherToTeacherDto(teacher)));
        return ResponseEntity.ok(teacherList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TeacherDtoResponse> getTeacherById(@PathVariable("id") Long id){
        TeacherDtoResponse response = mapper.teacherToTeacherDto(teacherService.getById(id));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public TeacherDtoResponse createTeacher(@RequestBody TeacherDtoRequest teacher){
        return mapper.teacherToTeacherDto(teacherService.createTeacher(mapper.teacherDtoToTeacher(teacher)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void deleteTeacher(@PathVariable("id") Long id)
    {
        teacherService.delete(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("#id == authentication.principal.id or hasAnyAuthority('ADMIN')")
    public TeacherDtoResponse updateTeacher(@PathVariable("id") Long id,
                                      @RequestBody TeacherDtoRequest teacher){
        return mapper.teacherToTeacherDto(teacherService.updateTeacher(id, mapper.teacherDtoToTeacher(teacher)));
    }

}
