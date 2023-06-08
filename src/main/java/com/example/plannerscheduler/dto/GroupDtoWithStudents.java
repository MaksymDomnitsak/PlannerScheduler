package com.example.plannerscheduler.dto;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupDtoWithStudents {
    Long id;

    String name;

    List<StudentDtoResponseForGroup> students;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudentDtoResponseForGroup> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDtoResponseForGroup> students) {
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
