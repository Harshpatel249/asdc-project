package com.group6.commune.Service;

import com.group6.commune.Exceptions.DataNotFoundException;
import com.group6.commune.Exceptions.ValidationException;
import com.group6.commune.Model.Event;
import com.group6.commune.Repository.EventRepository;
import com.group6.commune.Validators.EventValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    @Qualifier("eventValidator")
    private EventValidator eventValidator;

    public List<Event> getAllEvents() {
        List<Event> events = eventRepository.getAllEvents();
        if (events.isEmpty()){
            throw new DataNotFoundException("There are no events to attend.");
        }
        return eventRepository.getAllEvents();
    }

    @Override
    public Event createEvent(Event event, BindingResult result) {
        eventValidator.validate(event, result);
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            throw new ValidationException("Provided event information is not valid", errors);
        }
        return eventRepository.createEvent(event);
    }

    @Override
    public Event getEventById(int id) {
        Event event = null;
        try{
            event = eventRepository.getEventById(id);
        } catch (EmptyResultDataAccessException ex){
            throw new DataNotFoundException("Event with ID: "+id+" not found");
        }

        return event;
    }

    @Override
    public Event updateEvent(Event event, BindingResult result) {
        if (event == null) {
            throw new DataNotFoundException("Event does not exist");
        }
        eventValidator.validate(event, result);
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            throw new ValidationException("Validation error", errors);
        }
        Event updatedEvent = eventRepository.updateEvent(event);
        if (updatedEvent == null) {
            throw new DataNotFoundException("Event does not exist");
        }
        return updatedEvent;
    }

    @Override
    public Event deleteEvent(int id) {
        Event event = getEventById(id);
        if (event == null) {
            throw new DataNotFoundException("Event does not exist");
        }
        Event deletedEvent = eventRepository.deleteEvent(id);
        if (deletedEvent == null) {
            throw new DataNotFoundException("Event does not exist");
        }
        return deletedEvent;
    }

}
