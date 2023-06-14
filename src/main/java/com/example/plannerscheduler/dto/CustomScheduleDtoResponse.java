package com.example.plannerscheduler.dto;

import com.example.plannerscheduler.enums.LessonType;

import java.time.LocalTime;
import java.util.List;

public class CustomScheduleDtoResponse {
    Long id;

    String customTitle;

    SubjectDtoResponse subject;

    UserDtoResponse creator;

    List<UserDtoResponse> attendees;

    GroupDtoWithStudents group;

    Long dayOfWeek;

    Boolean isEvenWeek;

    Long lessonOrder;

    LessonType typeOfLesson;

    Boolean isOnline;

    String auditoryNumber;

    LocalTime startTime;

    LocalTime endTime;

    public CustomScheduleDtoResponse() {
    }

    public CustomScheduleDtoResponse(Long id, String customTitle, SubjectDtoResponse subject, UserDtoResponse creator, GroupDtoWithStudents group, Long dayOfWeek, Boolean isEvenWeek, Long lessonOrder, LessonType typeOfLesson, Boolean isOnline, String auditoryNumber) {
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

    public CustomScheduleDtoResponse(Long id, String customTitle, SubjectDtoResponse subject, UserDtoResponse creator, GroupDtoWithStudents group, Long dayOfWeek, Boolean isEvenWeek, Long lessonOrder, LessonType typeOfLesson, Boolean isOnline, String auditoryNumber, LocalTime startTime, LocalTime endTime) {
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
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public CustomScheduleDtoResponse(Long id, String customTitle, SubjectDtoResponse subject, UserDtoResponse creator, List<UserDtoResponse> attendees, GroupDtoWithStudents group, Long dayOfWeek, Boolean isEvenWeek, Long lessonOrder, LessonType typeOfLesson, Boolean isOnline, String auditoryNumber, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.customTitle = customTitle;
        this.subject = subject;
        this.creator = creator;
        this.attendees = attendees;
        this.group = group;
        this.dayOfWeek = dayOfWeek;
        this.isEvenWeek = isEvenWeek;
        this.lessonOrder = lessonOrder;
        this.typeOfLesson = typeOfLesson;
        this.isOnline = isOnline;
        this.auditoryNumber = auditoryNumber;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public SubjectDtoResponse getSubject() {
        return subject;
    }

    public void setSubject(SubjectDtoResponse subject) {
        this.subject = subject;
    }

    public UserDtoResponse getCreator() {
        return creator;
    }

    public void setCreator(UserDtoResponse creator) {
        this.creator = creator;
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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public List<UserDtoResponse> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<UserDtoResponse> attendees) {
        this.attendees = attendees;
    }
}
