package com.group6.commune.Service;

import com.group6.commune.Model.Community;
import com.group6.commune.Repository.CommunityRepository;
import com.group6.commune.Repository.ICommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityService {

    @Autowired
    @Qualifier("CommunityRepository")
    private ICommunityRepository communityRepository;

    public CommunityService(){
        communityRepository = new CommunityRepository();
    }
    public Boolean createCommunity(Community community){
        return communityRepository.createCommunity(community);
    }

    public Community getCommunity(int communityID){
        return communityRepository.getCommunity(communityID);
    }

    public Boolean updateCommunity(Community community){
        return communityRepository.updateCommunity(community);
    }

    public Boolean deleteCommunity(int communityID){
        return communityRepository.deleteCommunity(communityID);
    }

    public List<Community> getAllCommunity(){
        return communityRepository.getAllCommunity();
    }

    public List<Community> getAllCommunity(String keyword){
        return communityRepository.getAllCommunity(keyword);
    }
}
