package com.group6.commune.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class Event {


    @NotNull
    @Min(value=0, message = "Id should be greater than 0.")
    private int eventId;

    @NotNull (message = "Event name should not be null")
    @NotEmpty (message = "Event name should not be empty")
    private String name;

    private String shortDescription;

    @NotNull (message = "Event description should not be null")
    @NotEmpty (message = "Event description should not be empty")
    private String description;

    @NotNull (message = "Event location should not be null")
    @NotEmpty (message = "Event location should not be empty")
    private String location;

    @NotNull (message = "Event start time should not be null")
    @NotEmpty (message = "Event start time should not be empty")
    private Date eventStartTime;

    @NotNull (message = "Event end time should not be null")
    @NotEmpty (message = "Event end time should not be empty")
    private Date eventEndTime;
    private String eventPoster;
    private int entryFees;

    @NotNull (message = "Event type should not be null")
    @NotEmpty (message = "Event type should not be empty")
    private String eventType;

    @NotNull (message = "User id should not be null")
    @Min( value=0, message = "Id should be greater than 0.")
    private int createdByUserId;

    public Event() {
    }

    public Event(int eventId, String name, String shortDescription, String description, String location, Date eventStartTime, Date eventEndTime, String eventPoster, int entryFees, String eventType, int createdByUserId) {
        this.eventId = eventId;
        this.name = name;
        this.shortDescription = shortDescription;
        this.description = description;
        this.location = location;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventPoster = eventPoster;
        this.entryFees = entryFees;
        this.eventType = eventType;
        this.createdByUserId = createdByUserId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(Date eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public Date getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(Date eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public String getEventPoster() {
        return eventPoster;
    }

    public void setEventPoster(String eventPoster) {
        this.eventPoster = eventPoster;
    }

    public int getEntryFees() {
        return entryFees;
    }

    public void setEntryFees(int entryFees) {
        this.entryFees = entryFees;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(int createdByUserId) {
        this.createdByUserId = createdByUserId;
    }
}