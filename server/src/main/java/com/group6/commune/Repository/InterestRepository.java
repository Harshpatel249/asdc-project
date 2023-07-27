package com.group6.commune.Repository;

import com.group6.commune.Model.Interest;

import java.util.List;

public interface InterestRepository {
    List<Interest> getInterestList();

    boolean saveUserInterest(int userId, int interestId);
}
