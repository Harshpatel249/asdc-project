package com.group6.commune.Service;

import com.group6.commune.Model.Community;
import com.group6.commune.Model.Interest;

import java.util.List;

public interface ICommunityService {

    public Boolean createCommunity(Community community);
    public Community getCommunity(int communityID);
    public Boolean updateCommunity(Community community);
    public Boolean deleteCommunity(int communityID);
    public List<Community> getAllCommunity();
    public List<Community> getAllCommunity(String keyword);
    public List<Community> getAllUserCommunity(int userID);
    public Boolean addCommunityInterest(int communityID, int interestID);
    public List<Interest> getCommunityInterests(int communityID);
    public Boolean deleteCommunityInterest(int communityID, int interestID);
}
