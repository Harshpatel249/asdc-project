package com.group6.commune.Repository;

import com.group6.commune.Model.Interest;
import com.group6.commune.Model.UserInterests;

import java.util.List;

public interface IInterestRepository {
    List<Interest> getInterestList();

    void saveUserInterest(UserInterests userInterests);
}
