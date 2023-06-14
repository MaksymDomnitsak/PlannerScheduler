package com.example.plannerscheduler.controllers;


import com.example.plannerscheduler.dto.GroupDto;
import com.example.plannerscheduler.dto.GroupDtoWithStudents;
import com.example.plannerscheduler.mappers.GroupDtoToGroupMapper;
import com.example.plannerscheduler.mappers.StudentDtoToStudentMapper;
import com.example.plannerscheduler.service.GroupService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/group")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class GroupController {

    GroupService service;

    private GroupDtoToGroupMapper mapper = Mappers.getMapper(GroupDtoToGroupMapper.class);


    public GroupController(GroupService service) {
        this.service = service;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GroupDto>> getAll(){
        List<GroupDto> groupList = new ArrayList<>();
                service.getAll().forEach(group -> groupList.add(mapper.groupToGroupDtoMapper(group)));
        return ResponseEntity.ok(groupList);
    }

    @GetMapping(params = {"page","size"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GroupDto>> getPaginated(@RequestParam("page") int page , @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<GroupDto> groupList = new ArrayList<>();
        service.getAll(pageable).forEach(group -> groupList.add(mapper.groupToGroupDtoMapper(group)));
        return ResponseEntity.ok(groupList);
    }
    @GetMapping(params = {"teacherId"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GroupDto>> getByTeacherId(@RequestParam("teacherId") Long teacherId) {
        List<GroupDto> groupList = new ArrayList<>();
        service.getAllByTeacher(teacherId).forEach(group -> groupList.add(mapper.groupToGroupDtoMapper(group)));
        return ResponseEntity.ok(groupList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GroupDtoWithStudents> getGroupById(@PathVariable("id") Long id){
        GroupDtoWithStudents groupDto = mapper.groupToGroupDtoWithStudentsMapper(service.getById(id));
        return ResponseEntity.ok(groupDto);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public GroupDto createGroup(@RequestBody GroupDto groupDto){
        return mapper.groupToGroupDtoMapper(service.createGroup(mapper.groupDtoToGroupMapper(groupDto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void deleteGroup(@PathVariable("id") Long id)
    {
        service.delete(id);
    }

    /*@PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)*/

}
