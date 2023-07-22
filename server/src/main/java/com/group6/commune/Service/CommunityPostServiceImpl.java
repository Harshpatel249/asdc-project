package com.group6.commune.Service;

import com.group6.commune.Model.CommunityPosts;
import com.group6.commune.Repository.CommunityPostsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommunityPostServiceImpl implements CommunityPostsService{

    @Autowired
    CommunityPostsImpl communityPosts;

    @Override
    public List<CommunityPosts> getAllPosts() {
        List<CommunityPosts> posts = communityPosts.getAllPosts();
        System.out.println("posts: "+ posts);
        return posts.size() > 0 ? posts : new ArrayList<>();
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
