package com.group6.commune.Controller;

import com.group6.commune.Model.Event;
import com.group6.commune.Service.EventServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventServiceImpl eventService;

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents(){
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody @Valid Event event){
        return new ResponseEntity<>(eventService.createEvent(event), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable int id){
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PutMapping
    public Event updateEvent(@RequestBody Event event){
        return eventService.updateEvent(event);
    }

    @DeleteMapping("/{id}")
    public Event deleteEvent(@PathVariable int id){
        return eventService.deleteEvent(id);
    }

}
