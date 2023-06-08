package com.example.plannerscheduler.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class NoteDtoRequest {

    String title;

    Long lesson_id;

    Long student_id;

    String body;

    @JsonProperty("isFinished")
    Boolean finished;


    public NoteDtoRequest() {
    }

    public NoteDtoRequest(String title, Long lesson_id, Long student_id, String body, Boolean finished) {
        this.title = title;
        this.lesson_id = lesson_id;
        this.student_id = student_id;
        this.body = body;
        this.finished = finished;
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
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Long getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(Long lesson_id) {
        this.lesson_id = lesson_id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }
}
