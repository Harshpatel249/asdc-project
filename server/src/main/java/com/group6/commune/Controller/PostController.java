package com.group6.commune.Controller;

import com.group6.commune.Model.CommunityPosts;
import com.group6.commune.Service.CommunityPostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    CommunityPostServiceImpl communityPostService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/")
    public ResponseEntity<List<CommunityPosts>> getAllPosts(){
        return ResponseEntity.ok(communityPostService.getAllPosts());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<CommunityPosts> createPost(@RequestBody CommunityPosts posts, BindingResult result){
        return new ResponseEntity<>(communityPostService.createPosts(posts,result), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommunityPosts> getPosts(@PathVariable int id){
        return ResponseEntity.ok(communityPostService.getPostById(id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(path = "/updatepost")
    public CommunityPosts updatePosts(@RequestBody CommunityPosts posts,BindingResult result){
        System.out.println("id"+ posts.getPostId());
        System.out.println(posts.getPostTitle());
        System.out.println(posts.getPostImage());
        return communityPostService.updatePost(posts,result);
//        return null;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deletePosts(@PathVariable int id){
        return ResponseEntity.ok(communityPostService.deletePosts(id));
    }
}
