package com.group6.commune.Service;

import com.group6.commune.Model.Interest;
import com.group6.commune.Model.UserInterests;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface InterestService {
    List<Interest> getInterestList();

    boolean addUserInterest(UserInterests userInterests, BindingResult result);
}
