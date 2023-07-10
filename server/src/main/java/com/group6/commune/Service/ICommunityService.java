package com.group6.commune.Service;

import com.group6.commune.Model.Community;

import java.util.List;

public interface ICommunityService {

    public Boolean createCommunity(Community community);
    public Community getCommunity(int communityID);
    public Boolean updateCommunity(Community community);
    public Boolean deleteCommunity(int communityID);
    public List<Community> getAllCommunity();
    public List<Community> getAllCommunity(String keyword);
}
