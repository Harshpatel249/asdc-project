package com.group6.commune.Controller;

import com.group6.commune.Model.CommunityComments;
import com.group6.commune.Model.CommunityPosts;
import com.group6.commune.Service.CommunityCommentsService;
import com.group6.commune.Service.CommunityPostServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    CommunityCommentsService communityCommentsService;

    @PostMapping
    public ResponseEntity<CommunityComments> createComment(@RequestBody @Valid CommunityComments comments){
        return new ResponseEntity<>(communityCommentsService.createComment(comments), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public CommunityComments deleteComment(@PathVariable int id){
        return communityCommentsService.deleteComment(id);
    }
}
