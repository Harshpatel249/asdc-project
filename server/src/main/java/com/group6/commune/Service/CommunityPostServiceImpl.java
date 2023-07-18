package com.group6.commune.Service;

import com.group6.commune.Model.CommunityPosts;
import com.group6.commune.Repository.CommunityPostsImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CommunityPostServiceImpl implements CommunityPostsService{

    @Autowired
    CommunityPostsImpl communityPosts;

    @Override
    public List<CommunityPosts> getAllPosts() {
        List<CommunityPosts> events = communityPosts.getAllPosts();
        return events.size() > 0 ? events : new ArrayList<>();
    }

    @Override
    public CommunityPosts createPosts(CommunityPosts posts) {
        return communityPosts.createPosts(posts);
    }

    @Override
    public CommunityPosts getPostById(int id) {
        return communityPosts.getPostById(id);
    }

    @Override
    public CommunityPosts updatePost(CommunityPosts posts) {
        return communityPosts.updatePosts(posts);
    }

    @Override
    public CommunityPosts deletePosts(int id) {
        return communityPosts.deletePosts(id);
    }
}
