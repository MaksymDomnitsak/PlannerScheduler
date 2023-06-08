package com.example.plannerscheduler.dto;

import com.example.plannerscheduler.enums.LessonType;

import java.time.LocalTime;
import java.util.List;

public class CustomScheduleDtoRequest {
    String customTitle;

    Long subjectId;

    Long creatorId;

    List<Long> attendees;

    Long groupId;

    Long dayOfWeek;

    Boolean isEvenWeek;

    Long lessonOrder;

    LessonType typeOfLesson;

    Boolean isOnline;

    String auditoryNumber;

    LocalTime startTime;

    LocalTime endTime;

    public CustomScheduleDtoRequest() {
    }

    public CustomScheduleDtoRequest(String customTitle, Long subjectId, Long creatorId, Long groupId, Long dayOfWeek, Boolean isEvenWeek, Long lessonOrder, LessonType typeOfLesson, Boolean isOnline, String auditoryNumber) {
        this.customTitle = customTitle;
        this.subjectId = subjectId;
        this.creatorId = creatorId;
        this.groupId = groupId;
        this.dayOfWeek = dayOfWeek;
        this.isEvenWeek = isEvenWeek;
        this.lessonOrder = lessonOrder;
        this.typeOfLesson = typeOfLesson;
        this.isOnline = isOnline;
        this.auditoryNumber = auditoryNumber;
    }

    public CustomScheduleDtoRequest(String customTitle, Long subjectId, Long creatorId, Long groupId, Long dayOfWeek, Boolean isEvenWeek, Long lessonOrder, LessonType typeOfLesson, Boolean isOnline, String auditoryNumber, LocalTime startTime, LocalTime endTime) {
        this.customTitle = customTitle;
        this.subjectId = subjectId;
        this.creatorId = creatorId;
        this.groupId = groupId;
        this.dayOfWeek = dayOfWeek;
        this.isEvenWeek = isEvenWeek;
        this.lessonOrder = lessonOrder;
        this.typeOfLesson = typeOfLesson;
        this.isOnline = isOnline;
        this.auditoryNumber = auditoryNumber;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public CustomScheduleDtoRequest(String customTitle, Long subjectId, Long creatorId, List<Long> attendees, Long groupId, Long dayOfWeek, Boolean isEvenWeek, Long lessonOrder, LessonType typeOfLesson, Boolean isOnline, String auditoryNumber, LocalTime startTime, LocalTime endTime) {
        this.customTitle = customTitle;
        this.subjectId = subjectId;
        this.creatorId = creatorId;
        this.attendees = attendees;
        this.groupId = groupId;
        this.dayOfWeek = dayOfWeek;
        this.isEvenWeek = isEvenWeek;
        this.lessonOrder = lessonOrder;
        this.typeOfLesson = typeOfLesson;
        this.isOnline = isOnline;
        this.auditoryNumber = auditoryNumber;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getCustomTitle() {
        return customTitle;
    }

    public void setCustomTitle(String customTitle) {
        this.customTitle = customTitle;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

    public List<Long> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Long> attendees) {
        this.attendees = attendees;
    }
}
