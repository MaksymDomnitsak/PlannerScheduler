package com.example.plannerscheduler.models;

import com.example.plannerscheduler.enums.LessonType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "schedule")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(name = "custom_title")
    private String customTitle;

    @ManyToOne
    @JoinColumn(name="subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name="creator_id", nullable = false)
    private User creator;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name = "event_id"))
    private List<User> attendees;

    @ManyToOne
    @JoinColumn(name="group_id", nullable = false)
    private Group group;

    @Column(name = "day_of_week")
    Long dayOfWeek;

    @Column(name = "is_even_week")
    Boolean isEvenWeek;

    @Column(name = "lesson_order")
    Long lessonOrder;

    @Column(name = "type_of_lesson", nullable = false)
    @Enumerated(EnumType.STRING)
    LessonType typeOfLesson;

    @Column(name = "is_online")
    Boolean isOnline;

    @Column(name = "auditory_number")
    String auditoryNumber;

    @Column(name = "start_of_event")
    LocalTime startTime;

    @Column(name = "end_of_event")
    LocalTime endTime;

    public Schedule() {
    }

    public Schedule(Long id, Subject subject, User creator, Group group, Long dayOfWeek, Boolean isEvenWeek, Long lessonOrder, LessonType typeOfLesson, Boolean isOnline, String auditoryNumber, LocalTime startTime, LocalTime endTime) {
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
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Schedule(Subject subject, User creator, Group group, Long dayOfWeek, Boolean isEvenWeek, Long lessonOrder, LessonType typeOfLesson, Boolean isOnline, String auditoryNumber, LocalTime startTime, LocalTime endTime) {
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

    public Schedule(Long id, Subject subject, User creator, List<User> attendees, Group group, Long dayOfWeek, Boolean isEvenWeek, Long lessonOrder, LessonType typeOfLesson, Boolean isOnline, String auditoryNumber, LocalTime startTime, LocalTime endTime) {
        this.id = id;
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

    public Schedule(Long id, String customTitle, User creator, List<User> attendees, Group group, Long dayOfWeek, Boolean isEvenWeek, Long lessonOrder, LessonType typeOfLesson, Boolean isOnline, String auditoryNumber, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.customTitle = customTitle;
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

    public Schedule(Long id, String customTitle, Subject subject, User creator, List<User> attendees, Group group, Long dayOfWeek, Boolean isEvenWeek, Long lessonOrder, LessonType typeOfLesson, Boolean isOnline, String auditoryNumber, LocalTime startTime, LocalTime endTime) {
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
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
    
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<User> attendees) {
        this.attendees = attendees;
    }

    public String getCustomTitle() {
        return customTitle;
    }

    public void setCustomTitle(String customTitle) {
        this.customTitle = customTitle;
    }
}
