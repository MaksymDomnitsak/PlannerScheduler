package com.example.plannerscheduler.controllers;


import com.example.plannerscheduler.dto.StudentDtoRequest;
import com.example.plannerscheduler.dto.StudentDtoRequest;
import com.example.plannerscheduler.dto.StudentDtoResponse;
import com.example.plannerscheduler.mappers.StudentDtoToStudentMapper;
import com.example.plannerscheduler.models.Student;
import com.example.plannerscheduler.service.StudentService;
import com.example.plannerscheduler.service.StudentService;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/student", produces = MediaType.APPLICATION_JSON_VALUE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class StudentController {

    StudentService studentService;

    private StudentDtoToStudentMapper mapper = Mappers.getMapper(StudentDtoToStudentMapper.class);


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StudentDtoResponse>> getAll() {
        List<StudentDtoResponse> studentList = new ArrayList<>();
        studentService.getAll().forEach(student -> studentList.add(mapper.studentToStudentDto(student)));
        return ResponseEntity.ok(studentList);
    }

    @GetMapping(params = {"page","size"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StudentDtoResponse>> getPaginated(@RequestParam("page") int page , @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page,size);
        List<StudentDtoResponse> studentList = new ArrayList<>();
        studentService.getAll(pageable).forEach(student -> studentList.add(mapper.studentToStudentDto(student)));
        return ResponseEntity.ok(studentList);
    }

    @GetMapping(params = {"group"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StudentDtoResponse>> getAllByGroupId(@RequestParam("group") Long group){
        List<StudentDtoResponse> studentList = new ArrayList<>();
        studentService.getByGroup(group).forEach(student -> studentList.add(mapper.studentToStudentDto(student)));
        return ResponseEntity.ok(studentList);
    }

    @GetMapping(params = {"group","page","size"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StudentDtoResponse>> getPaginated(@RequestParam("group") Long group ,@RequestParam("page") int page , @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page,size);
        List<StudentDtoResponse> studentList = new ArrayList<>();
        studentService.getPageByGroup(group,pageable).forEach(student -> studentList.add(mapper.studentToStudentDto(student)));
        return ResponseEntity.ok(studentList);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public StudentDtoResponse createStudent(@RequestBody StudentDtoRequest student){
        return mapper.studentToStudentDto(studentService.createStudent(mapper.studentDtoToStudent(student)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void deleteStudent(@PathVariable("id") Long id)
    {
        studentService.delete(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("#id == authentication.principal.id or hasAnyAuthority('ADMIN')")
    public StudentDtoResponse updateStudent(@PathVariable("id") Long id,
                                      @RequestBody StudentDtoRequest student){
        return mapper.studentToStudentDto(studentService.updateStudent(id, mapper.studentDtoToStudent(student)));
    }

}
