package com.group6.commune.Controller;

import com.group6.commune.Model.EventMembers;
import com.group6.commune.Service.EventMemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events/{eventId}/users")
public class EventMembersController {

    @Autowired
    EventMemberServiceImpl eventMemberService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<EventMembers> addMember(@RequestBody EventMembers eventMember){
        return ResponseEntity.ok(eventMemberService.addMember(eventMember));
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
}
