package com.group6.commune.Service;

import com.group6.commune.Model.CommunityPosts;

import java.util.List;

public interface CommunityPostsService {
    List<CommunityPosts> getAllPosts();
    CommunityPosts createPosts(CommunityPosts posts);

    CommunityPosts getPostById(int id);
    CommunityPosts updatePost(CommunityPosts posts);

    CommunityPosts deletePosts(int id);



}
