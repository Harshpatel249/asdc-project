package com.group6.commune.Service;

import com.group6.commune.Model.CommunityPosts;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface CommunityPostsService {
    List<CommunityPosts> getAllPosts();
    CommunityPosts createPosts(CommunityPosts posts, BindingResult result);

    CommunityPosts getPostById(int id);

    List<CommunityPosts> getPostByCommunityId(int id);
    CommunityPosts updatePost(CommunityPosts posts, BindingResult result);

    int deletePosts(int id);



}
