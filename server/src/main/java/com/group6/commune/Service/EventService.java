package com.group6.commune.Service;

import com.group6.commune.Model.Event;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    Event createEvent(Event event, BindingResult result);
    Event getEventById(int id);
    Event updateEvent(Event event, BindingResult result );
    Event deleteEvent(int id);
    Boolean addEventInterests(int id, int interest_id);
}
