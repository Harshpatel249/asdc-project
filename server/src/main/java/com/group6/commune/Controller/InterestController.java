package com.group6.commune.Controller;

import com.group6.commune.Model.Interest;
import com.group6.commune.Model.UserInterests;
import com.group6.commune.Service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interest")
public class InterestController {
    @Autowired
    private InterestService interestService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<Interest>> getInterestList() {
        return ResponseEntity.ok(interestService.getInterestList());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void addUserInterest(@RequestBody UserInterests userInterests) {
        interestService.addUserInterest(userInterests.getUserId(), userInterests.getInterestId());
    }
}
