package com.group6.commune.Repository;

import com.group6.commune.Model.Event;

import java.util.List;

public interface EventRepository {
    List<Event> getAllEvents();
    Event createEvent(Event event);
    Event getEventById(int id);
    Event updateEvent(Event event);
    Event deleteEvent(int id);

    Boolean addEventInterests(int id, int interest_id);
}
