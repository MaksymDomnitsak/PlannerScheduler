package com.example.plannerscheduler.dto;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class NoteDtoResponse {

    Long id;
    String title;

    ScheduleDtoResponse lesson;

    StudentDtoResponse student;

    String body;

    boolean isFinished;

    public NoteDtoResponse(Long id, String title, ScheduleDtoResponse lesson, StudentDtoResponse student, String body, boolean isFinished) {
        this.id = id;
        this.title = title;
        this.lesson = lesson;
        this.student = student;
        this.body = body;
        this.isFinished = isFinished;
    }

    public NoteDtoResponse() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean getFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public ScheduleDtoResponse getLesson() {
        return lesson;
    }

    public void setLesson(ScheduleDtoResponse lesson) {
        this.lesson = lesson;
    }

    public StudentDtoResponse getStudent() {
        return student;
    }

    public void setStudent(StudentDtoResponse student) {
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
