package com.group6.commune.ControllerTests;

import com.group6.commune.Controller.CommunityController;
import com.group6.commune.Controller.PostController;
import com.group6.commune.Exceptions.DataNotFoundException;
import com.group6.commune.Exceptions.ValidationException;
import com.group6.commune.Model.Community;
import com.group6.commune.Model.CommunityPosts;
import com.group6.commune.Model.Event;
import com.group6.commune.Service.CommunityPostServiceImpl;
import com.group6.commune.Service.CommunityPostsService;
import com.group6.commune.Service.CommunityServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


public class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CommunityPostServiceImpl communityPostService;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @Test
    public void testCreatePost() throws Exception {
        CommunityPosts posts = new CommunityPosts();
        posts.setPostId(1);
        posts.setPostTitle("Test");

        given(communityPostService.createPosts(any(CommunityPosts.class), any(BindingResult.class))).willReturn(posts);

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"postId\":1,\"postTitle\":\"Test\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.postId").value(1))
                .andExpect(jsonPath("$.postTitle").value("Test"));
    }


    @Test
    public void getPostByIDTest() throws Exception {
        CommunityPosts posts = new CommunityPosts();
        posts.setPostId(1);
        posts.setPostTitle("Test");

        given(communityPostService.getPostById(1)).willReturn(posts);

        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.postId").value(1))
                .andExpect(jsonPath("$.postTitle").value("Test"));
    }

    @Test
    public void testGetAllPostsEmptyList() {
        List<CommunityPosts> emptyList = new ArrayList<>();
        when(communityPostService.getAllPosts()).thenReturn(emptyList);

        ResponseEntity<List<CommunityPosts>> response = postController.getAllPosts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(emptyList, response.getBody());
    }

    @Test
    public void testGetAllPosts() {

        List<CommunityPosts> expectedPosts = new ArrayList<>();
        expectedPosts.add(new CommunityPosts(1, 101, 201, "Post 1", "Description 1", "image1.jpg"));
        expectedPosts.add(new CommunityPosts(2, 102, 201, "Post 2", "Description 2", "image2.jpg"));
        when(communityPostService.getAllPosts()).thenReturn(expectedPosts);

        ResponseEntity<List<CommunityPosts>> response = postController.getAllPosts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPosts, response.getBody());
    }


    @Test
    void testGetPostsByCommunity_InvalidCommunityId() {

        int invalidCommunityId = -1; // Replace with an invalid community ID in your test data

        ResponseEntity<List<CommunityPosts>> response = postController.getPostsByCommunity(invalidCommunityId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    public void testDeletePost_Success() {

        int postIdToDelete = 1;
        CommunityPosts testPost = new CommunityPosts(postIdToDelete, 1, 1, "Test Post", "Test description", "test_image.jpg");

        when(communityPostService.deletePosts(postIdToDelete)).thenReturn(1);

        ResponseEntity<Integer> response = postController.deletePosts(postIdToDelete);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody());
    }

}






