package com.group6.commune.Controller;

import com.group6.commune.Model.Interest;
import com.group6.commune.Model.UserInterests;
import com.group6.commune.Service.InterestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interest")
public class InterestController {
    @Autowired
    private InterestServiceImpl interestService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<Interest>> getInterestList() {
        return ResponseEntity.ok(interestService.getInterestList());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<String> addUserInterests(@RequestBody UserInterests userInterests, BindingResult result) {
            interestService.addUserInterest(userInterests, result);
            return ResponseEntity.status(HttpStatus.CREATED).body("User interests added successfully.");
    }
}
