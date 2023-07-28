package com.group6.commune.Controller;

import com.group6.commune.Model.CommunityComments;
import com.group6.commune.Service.CommunityCommentsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteComment(@PathVariable int id){
        return ResponseEntity.ok(communityCommentsService.deleteComment(id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/list/{id}")
    public ResponseEntity<List<CommunityComments>> getCommentListById(@PathVariable int id){
        return ResponseEntity.ok(communityCommentsService.getCommentListById(id));
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<CommunityComments> getCommentsById(@PathVariable int id){
        return ResponseEntity.ok(communityCommentsService.getCommentsById(id));
    }

}
