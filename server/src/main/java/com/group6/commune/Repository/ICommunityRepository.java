package com.group6.commune.Repository;

import com.group6.commune.Model.Community;

import java.util.List;

public interface ICommunityRepository {
    public Boolean createCommunity(Community community);
    public Community getCommunity(int communityID);
    public Boolean updateCommunity(Community community);
    public Boolean deleteCommunity(int communityID);
    public List<Community> getAllCommunity();
    public List<Community> getAllCommunity(String keyword);
}
