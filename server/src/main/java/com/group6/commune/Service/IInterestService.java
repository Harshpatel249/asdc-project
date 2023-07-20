package com.group6.commune.Service;

import com.group6.commune.Model.Interest;

import java.util.List;

public interface IInterestService {
    List<Interest> getInterestList();

    Interest addUserInterest(int userId, int interestId);
}
