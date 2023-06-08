package com.example.plannerscheduler.dto;

import java.util.List;

public class EventDto {
    private String summary;
    private String location;
    private String description;
    private String startDate;
    private String frequency;
    private int repeats;
    private Long[] groupsId;
    private String[] attendeesEmails;
    private String conference;
    private Long scheduleId;

    public EventDto() {
    }

    public EventDto(String summary, String location, String description, String startDate, String frequency, int repeats, Long[] groupsId, String[] attendeesEmails, String conference, Long scheduleId) {
        this.summary = summary;
        this.location = location;
        this.description = description;
        this.startDate = startDate;
        this.frequency = frequency;
        this.repeats = repeats;
        this.groupsId = groupsId;
        this.attendeesEmails = attendeesEmails;
        this.conference = conference;
        this.scheduleId = scheduleId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public int getRepeats() {
        return repeats;
    }

    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }

    public Long[] getGroupsId() {
        return groupsId;
    }

    public void setGroupsId(Long[] groupsId) {
        this.groupsId = groupsId;
    }

    public String[] getAttendeesEmails() {
        return attendeesEmails;
    }

    public void setAttendeesId(String[] attendeesEmails) {
        this.attendeesEmails = attendeesEmails;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }
}
