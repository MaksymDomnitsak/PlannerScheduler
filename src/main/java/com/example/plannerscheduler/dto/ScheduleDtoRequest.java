package com.example.plannerscheduler.dto;

import com.example.plannerscheduler.enums.LessonType;
import com.example.plannerscheduler.models.Group;
import com.example.plannerscheduler.models.Subject;
import com.example.plannerscheduler.models.Teacher;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ScheduleDtoRequest {
    String customTitle;

    Long subjectId;

    Long creatorId;

    Long groupId;

    Long dayOfWeek;

    Boolean isEvenWeek;

    Long lessonOrder;

    LessonType typeOfLesson;

    Boolean isOnline;

    String auditoryNumber;

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
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

    public Long getUserCreatorId() {
        return creatorId;
    }

    public void setUserCreatorId(Long userCreatorId) {
        this.creatorId = userCreatorId;
    }

    public String getCustomTitle() {
        return customTitle;
    }

    public void setCustomTitle(String customTitle) {
        this.customTitle = customTitle;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
