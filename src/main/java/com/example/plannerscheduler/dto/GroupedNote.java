package com.example.plannerscheduler.dto;

import java.util.List;

public class GroupedNote {
    String title;
    Long event;
    Long[] groups;
    Long[] students;
    String body;

    public GroupedNote(String title, Long event, Long[] groups, Long[] students, String body) {
        this.title = title;
        this.event = event;
        this.groups = groups;
        this.students = students;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getEvent() {
        return event;
    }

    public void setEvent(Long event) {
        this.event = event;
    }

    public Long[] getGroups() {
        return groups;
    }

    public void setGroups(Long[] groups) {
        this.groups = groups;
    }

    public Long[] getStudents() {
        return students;
    }

    public void setStudents(Long[] students) {
        this.students = students;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
