package com.group6.commune.ServiceTests;

import com.group6.commune.Exceptions.DataNotFoundException;
import com.group6.commune.Exceptions.ValidationException;
import com.group6.commune.Model.CommunityPosts;
import com.group6.commune.Repository.CommunityPost;
import com.group6.commune.Service.CommunityPostServiceImpl;
import com.group6.commune.Validators.PostsValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommunityPostsServiceImplTest {


    @Mock
    CommunityPost postRepository;

    @Mock
    PostsValidator postsValidator;

    @Autowired
    @InjectMocks
    CommunityPostServiceImpl communityPostService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createpostForInvalidParametersTest() {
        CommunityPosts communityPosts = new CommunityPosts();
        BindingResult result = new BeanPropertyBindingResult(communityPosts, "communityPosts");
        result.rejectValue("postTitle", "", "Post should not be empty or null.");

        assertThrows(ValidationException.class, () -> communityPostService.createPosts(communityPosts, result));
    }


    @Test
    void getpostByIdForRecordDoesNotExistTest() {
        when(postRepository.getPostById(1)).thenThrow(new DataNotFoundException("Post with ID: 1 not found"));

        assertThrows(DataNotFoundException.class, () -> postRepository.getPostById(1));
    }

    @Test
    void updatePostForInvalidDataTest() {
        CommunityPosts communityPosts = new CommunityPosts();
        communityPosts.setPostId(1);
        BindingResult result = new BeanPropertyBindingResult(communityPosts, "communityPosts");
        result.rejectValue("postTitle", "", "Post should not be empty or null.");

        assertThrows(ValidationException.class, () -> communityPostService.updatePost(communityPosts, result));
    }

    @Test
    void createPostForInvalidInputTest() {
        CommunityPosts communityPosts = new CommunityPosts();
        BindingResult result = new BeanPropertyBindingResult(communityPosts, "communityPosts");
        Map<String, String> errors = new HashMap<>();
        errors.put("postTitle", "Post should not be empty or null.");
        doThrow(new ValidationException("Validation error", errors)).when(postsValidator).validate(communityPosts, result);

        assertThrows(ValidationException.class, () -> communityPostService.createPosts(communityPosts, result));
    }

    @Test
    void updatePostValidationExceptionTest() {
        CommunityPosts communityPosts = new CommunityPosts();
        BindingResult result = new BeanPropertyBindingResult(communityPosts, "communityPosts");
        Map<String, String> errors = new HashMap<>();
        errors.put("postTitle", "Post should not be empty or null.");
        doThrow(new ValidationException("Validation error", errors)).when(postsValidator).validate(communityPosts, result);

        assertThrows(ValidationException.class, () -> communityPostService.updatePost(communityPosts, result));
    }


}
