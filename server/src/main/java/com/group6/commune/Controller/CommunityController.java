package com.group6.commune.Controller;

import com.group6.commune.AppLogger.AppLogger;
import com.group6.commune.Exceptions.DataNotFoundException;
import com.group6.commune.Exceptions.UnauthorizedAccessException;
import com.group6.commune.Exceptions.ValidationException;
import com.group6.commune.Model.Community;
import com.group6.commune.Model.Interest;
import com.group6.commune.Service.ICommunityService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/community")
public class CommunityController {
    @Autowired
    @Qualifier("CommunityService")
    private ICommunityService communityService;

    Logger logger = AppLogger.getLogger();

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<Integer> createCommunity(@RequestBody Community community, BindingResult result){
        logger.info("POST req on /community");
        return ResponseEntity.ok(communityService.createCommunity(community, result));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Community> getCommunity(@PathVariable int id){
        logger.info("GET req on /community/" + id);
        return new ResponseEntity<>(communityService.getCommunity(id), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<Community>> getAllCommunity(@RequestParam(required = false, name = "keyword") String keyword){
        logger.info("GET req on /community");
        if(keyword == null || keyword.isEmpty()){
            return ResponseEntity.ok(communityService.getAllCommunity());
        }else{
            return ResponseEntity.ok(communityService.getAllCommunity(keyword));
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping
    public ResponseEntity<Boolean> updateCommunity(@RequestBody Community community, BindingResult result){
        logger.info("PUT req on /community");
        return ResponseEntity.ok(communityService.updateCommunity(community, result));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCommunity(@PathVariable int id){
        logger.info("DELETE req on /community/" + id);
        return ResponseEntity.ok(communityService.deleteCommunity(id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<Community>> getAllCommunity(@PathVariable int user_id){
        logger.info("GET req on /community/user/" + user_id);
        return ResponseEntity.ok(communityService.getAllUserCommunity(user_id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/{id}/interest")
    public ResponseEntity<Boolean> addCommunityInterest(@PathVariable int id, @RequestParam(required = true, name = "interest_id") int interest_id){
        logger.info("POST req on /community/" + id + "/interest");
        return ResponseEntity.ok(communityService.addCommunityInterest(id, interest_id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}/interest")
    public ResponseEntity<List<Interest>> getCommunityInterests(@PathVariable int id){
        logger.info("GET req on /community/" + id + "/interest");
        return ResponseEntity.ok(communityService.getCommunityInterests(id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}/interest")
    public ResponseEntity<Boolean> deleteCommunity(@PathVariable int id, @RequestParam(required = false, name = "interest_id") int interest_id){
        logger.info("DELETE req on /community/" + id + "/interest");
        return ResponseEntity.ok(communityService.deleteCommunityInterest(id, interest_id));
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
