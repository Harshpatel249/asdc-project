package com.group6.commune.Service;

import com.group6.commune.Model.Community;
import com.group6.commune.Repository.ICommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("CommunityService")
public class CommunityServiceImpl implements ICommunityService{

    @Autowired
    @Qualifier("CommunityRepository")
    private ICommunityRepository communityRepository;

    public CommunityServiceImpl(){

    }
    @Override
    public Boolean createCommunity(Community community){
        return communityRepository.createCommunity(community);
    }

    @Override
    public Community getCommunity(int communityID){
        return communityRepository.getCommunity(communityID);
    }

    @Override
    public Boolean updateCommunity(Community community){
        return communityRepository.updateCommunity(community);
    }
    @Override
    public Boolean deleteCommunity(int communityID){
        return communityRepository.deleteCommunity(communityID);
    }
    @Override
    public List<Community> getAllCommunity(){
        return communityRepository.getAllCommunity();
    }
    @Override
    public List<Community> getAllCommunity(String keyword){
        return communityRepository.getAllCommunity(keyword);
    }
}
