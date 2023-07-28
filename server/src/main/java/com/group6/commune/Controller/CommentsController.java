package com.group6.commune.Controller;

import com.group6.commune.Exceptions.DataNotFoundException;
import com.group6.commune.Exceptions.UnauthorizedAccessException;
import com.group6.commune.Exceptions.ValidationException;
import com.group6.commune.Model.CommunityComments;
import com.group6.commune.Service.CommunityCommentsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    CommunityCommentsServiceImpl communityCommentsService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<CommunityComments> createComment(@RequestBody CommunityComments comments, BindingResult result){
        return new ResponseEntity<>(communityCommentsService.createComment(comments, result), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteComment(@PathVariable int id){
        return ResponseEntity.ok(communityCommentsService.deleteComment(id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<CommunityComments> getCommentById(@PathVariable int id){
        return ResponseEntity.ok(communityCommentsService.getCommentsById(id));
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
