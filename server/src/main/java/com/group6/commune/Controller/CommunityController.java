package com.group6.commune.Controller;

import com.group6.commune.Model.Community;
import com.group6.commune.Service.ICommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community")
public class CommunityController {
    @Autowired
    @Qualifier("CommunityService")
    private ICommunityService communityService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public Boolean createCommunity(@RequestBody Community community){
        return communityService.createCommunity(community);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public Community getCommunity(@PathVariable int id){
        return communityService.getCommunity(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<Community> getAllCommunity(@RequestParam(required = false, name = "keyword") String keyword){
        if(keyword == null || keyword.isEmpty()){
            return communityService.getAllCommunity();
        }else{
            return communityService.getAllCommunity(keyword);
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping
    public Boolean updateCommunity(@RequestBody Community community){
        return communityService.updateCommunity(community);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public Boolean deleteCommunity(@PathVariable int id){
        return communityService.deleteCommunity(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/user/{user_id}")
    public List<Community> getAllCommunity(@PathVariable int user_id){
        return communityService.getAllUserCommunity(user_id);
    }
}
