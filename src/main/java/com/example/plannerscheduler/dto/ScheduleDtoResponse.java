package com.example.plannerscheduler.dto;

import com.example.plannerscheduler.enums.LessonType;
import lombok.AllArgsConstructor;

public class ScheduleDtoResponse {
    Long id;

    String customTitle;

    SubjectDto subject;

    UserDtoResponse creator;

    GroupDtoWithStudents group;

    Long dayOfWeek;

    Boolean isEvenWeek;

    Long lessonOrder;

    LessonType typeOfLesson;

    Boolean isOnline;

    String auditoryNumber;

    public ScheduleDtoResponse() {
    }

    public ScheduleDtoResponse(Long id, SubjectDto subject, UserDtoResponse creator, GroupDtoWithStudents group, Long dayOfWeek, Boolean isEvenWeek, Long lessonOrder, LessonType typeOfLesson, Boolean isOnline, String auditoryNumber) {
        this.id = id;
        this.subject = subject;
        this.creator = creator;
        this.group = group;
        this.dayOfWeek = dayOfWeek;
        this.isEvenWeek = isEvenWeek;
        this.lessonOrder = lessonOrder;
        this.typeOfLesson = typeOfLesson;
        this.isOnline = isOnline;
        this.auditoryNumber = auditoryNumber;
    }

    public ScheduleDtoResponse(Long id, String customTitle, SubjectDto subject, UserDtoResponse creator, GroupDtoWithStudents group, Long dayOfWeek, Boolean isEvenWeek, Long lessonOrder, LessonType typeOfLesson, Boolean isOnline, String auditoryNumber) {
        this.id = id;
        this.customTitle = customTitle;
        this.subject = subject;
        this.creator = creator;
        this.group = group;
        this.dayOfWeek = dayOfWeek;
        this.isEvenWeek = isEvenWeek;
        this.lessonOrder = lessonOrder;
        this.typeOfLesson = typeOfLesson;
        this.isOnline = isOnline;
        this.auditoryNumber = auditoryNumber;
    }

    public SubjectDto getSubject() {
        return subject;
    }

    public void setSubject(SubjectDto subject) {
        this.subject = subject;
    }

    public GroupDtoWithStudents getGroup() {
        return group;
    }

    public void setGroup(GroupDtoWithStudents group) {
        this.group = group;
    }

    public Long getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Long dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Boolean getEvenWeek() {
        return isEvenWeek;
    }

    public void setEvenWeek(Boolean evenWeek) {
        isEvenWeek = evenWeek;
    }

    public Long getLessonOrder() {
        return lessonOrder;
    }

    public void setLessonOrder(Long lessonOrder) {
        this.lessonOrder = lessonOrder;
    }

    public LessonType getTypeOfLesson() {
        return typeOfLesson;
    }

    public void setTypeOfLesson(LessonType typeOfLesson) {
        this.typeOfLesson = typeOfLesson;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public String getAuditoryNumber() {
        return auditoryNumber;
    }

    public void setAuditoryNumber(String auditoryNumber) {
        this.auditoryNumber = auditoryNumber;
    }

    public UserDtoResponse getCreator() {
        return creator;
    }

    public void setCreator(UserDtoResponse creator) {
        this.creator = creator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomTitle() {
        return customTitle;
    }

    public void setCustomTitle(String customTitle) {
        this.customTitle = customTitle;
    }
}
