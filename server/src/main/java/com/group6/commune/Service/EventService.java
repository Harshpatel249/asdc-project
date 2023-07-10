package com.group6.commune.Service;

import com.group6.commune.Model.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    Event createEvent(Event event);
    Event getEventById(int id);
    Event updateEvent(Event event);
    Event deleteEvent(int id);
}
