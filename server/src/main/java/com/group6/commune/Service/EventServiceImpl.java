package com.group6.commune.Service;

import com.group6.commune.Model.Event;
import com.group6.commune.Repository.EventRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService{

    @Autowired
    EventRepositoryImpl eventRepository;

    public List<Event> getAllEvents(){
        List<Event> events = eventRepository.getAllEvents();
        return events.size() > 0 ? events : new ArrayList<>();
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.createEvent(event);
    }

    @Override
    public Event getEventById(int id) {
        return eventRepository.getEventById(id);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventRepository.updateEvent(event);
    }

    @Override
    public Event deleteEvent(int id){
        return eventRepository.deleteEvent(id);
    }

}