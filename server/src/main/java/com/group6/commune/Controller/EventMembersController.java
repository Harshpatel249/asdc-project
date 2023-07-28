package com.group6.commune.Controller;

import com.group6.commune.Exceptions.DataNotFoundException;
import com.group6.commune.Exceptions.UnauthorizedAccessException;
import com.group6.commune.Exceptions.ValidationException;
import com.group6.commune.Model.EventMembers;
import com.group6.commune.Service.EventMemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/events/{eventId}/users")
public class EventMembersController {

    @Autowired
    EventMemberServiceImpl eventMemberService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<EventMembers> addMember(@RequestBody EventMembers eventMember, BindingResult result){
        return ResponseEntity.ok(eventMemberService.addMember(eventMember, result));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<EventMembers>> getAllMembers(@PathVariable int eventId){
        return new ResponseEntity<>(eventMemberService.getAllMembers(eventId), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping
    public ResponseEntity<EventMembers> deleteMember(@RequestBody EventMembers eventMember){
        return ResponseEntity.ok(eventMemberService.deleteMember(eventMember));
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDataNotFoundException(DataNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(ValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", ex.getMessage(), "errors", ex.getErrors()));
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<Map<String, Object>> handleUnauthorizedAccessException(UnauthorizedAccessException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", ex.getMessage()));
    }
}