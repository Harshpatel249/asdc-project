package com.group6.commune.Controller;

import com.group6.commune.Model.CommunityPosts;
import com.group6.commune.Service.CommunityPostServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    CommunityPostServiceImpl communityPostService;

    @PostMapping(path = "/home")
    public String homeCheck(){
        return "hi";
    }
    @GetMapping("/all")
    public ResponseEntity<List<CommunityPosts>> getAllPosts(){
        return ResponseEntity.ok(communityPostService.getAllPosts());
    }

    @PostMapping
    public ResponseEntity<CommunityPosts> createEvent(@RequestBody @Valid CommunityPosts posts){
        return new ResponseEntity<>(communityPostService.createPosts(posts), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommunityPosts> getEvent(@PathVariable int id){
        return ResponseEntity.ok(communityPostService.getPostById(id));
    }

    @PutMapping
    public CommunityPosts updatePosts(@RequestBody CommunityPosts event){
        return communityPostService.updatePost(event);
    }

    @DeleteMapping("/{id}")
    public CommunityPosts deletePosts(@PathVariable int id){
        return communityPostService.deletePosts(id);
    }

}
