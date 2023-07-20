package com.group6.commune.Model;

import java.util.List;

public class UserInterests {
    private int userId;
    private int interestId;

    private List<Integer> interestList;

    public UserInterests() {}

    @Override
    public String toString() {
        return "UserInterests{" +
                "userId=" + userId +
                ", interestId=" + interestId +
                ", interestList=" + interestList +
                '}';
    }

    public List<Integer> getInterestList() {
        return interestList;
    }

    public void setInterestList(List<Integer> interestList) {
        this.interestList = interestList;
    }

    public int getInterestId() {
        return interestId;
    }

    public void setInterestId(int interestId) {
        this.interestId = interestId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserInterests(int userId, int interestId) {
        this.userId = userId;
        this.interestId = interestId;
    }
}
