package com.example.plannerscheduler.mappers.impl;

import com.example.plannerscheduler.dto.GroupDto;
import com.example.plannerscheduler.models.Group;
import com.example.plannerscheduler.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

public class GroupDtoToGroup {

    StudentService studentService;

    @Autowired
    public GroupDtoToGroup(StudentService studentService) {
        this.studentService = studentService;
    }

    public Group groupDtoToGroupMapper(GroupDto groupDto) {
        if ( groupDto == null ) {
            return null;
        }

        Group group = new Group();

        group.setName( groupDto.getName() );
        //group.setStudents(studentService.getByGroup(groupDto.));

        return group;
    }
}
