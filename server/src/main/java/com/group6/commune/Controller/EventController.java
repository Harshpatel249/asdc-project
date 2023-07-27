package com.group6.commune.Controller;

import com.group6.commune.Exceptions.DataNotFoundException;
import com.group6.commune.Exceptions.ValidationException;
import com.group6.commune.Model.Event;
import com.group6.commune.Model.Interest;
import com.group6.commune.Service.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventServiceImpl eventService;


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents(@RequestParam(required = false, name = "event_title") String eventTitle){
        if (eventTitle == null || eventTitle.isEmpty() || eventTitle.isBlank()){
            return ResponseEntity.ok(eventService.getAllEvents());
        }else{
            return ResponseEntity.ok(eventService.getEventByName(eventTitle));
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event, BindingResult result){
        return new ResponseEntity<>(eventService.createEvent(event, result), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable int id){
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("user/{userId}")
    public ResponseEntity<List<Event>> getUserCreatedEvents(@PathVariable int userId){
        return ResponseEntity.ok(eventService.getUserCreatedEvents(userId));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping
    public ResponseEntity<Event> updateEvent(@RequestBody Event event, BindingResult result){
        return ResponseEntity.ok(eventService.updateEvent(event, result));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteEvent(@PathVariable int id){
        return ResponseEntity.ok(eventService.deleteEvent(id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/{id}/interests")
    public ResponseEntity<Boolean> AddEventInterest(@PathVariable int id, @RequestParam(required = true, name= "interest_id") int interest_id) {
        return new ResponseEntity<>(eventService.addEventInterests(id,interest_id), HttpStatus.CREATED);
    }     
    
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}/interests")
    public ResponseEntity<List<Interest>> getEventInterests(@PathVariable int id) {
        return ResponseEntity.ok(eventService.getEventInterests(id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("{id}/interests")
    public ResponseEntity<Boolean> deleteEventInterest(@PathVariable int id, @RequestParam( required = false, name ="interest_id") int interest_id){
        return ResponseEntity.ok(eventService.deleteEventInterests(id,interest_id));
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDataNotFoundException(DataNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("message", ex.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(ValidationException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        body.put("errors", ex.getErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

}
