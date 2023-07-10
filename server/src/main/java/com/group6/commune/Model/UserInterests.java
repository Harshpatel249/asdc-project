package com.group6.commune.Model;

public class UserInterests {
    private int userId;
    private int interestId;

    public UserInterests() {}

    @Override
    public String toString() {
        return "UserInterests{" +
                "userId=" + userId +
                ", interestId=" + interestId +
                '}';
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
