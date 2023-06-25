package com.group6.commune.ControllerTests;

import com.group6.commune.Controller.CommunityController;
import com.group6.commune.Model.Community;
import com.group6.commune.Service.CommunityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.List;


import static org.mockito.Mockito.*;

@WebMvcTest(CommunityController.class)
@AutoConfigureMockMvc
public class CommunityControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommunityService communityService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCommunity() throws Exception {
        Community community = new Community(1, 1, "Community 1", "Description", "image.png");

        when(communityService.createCommunity(community)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/community")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"community_id\": 1, \"created_by\": 1, \"name\": \"Community 1\", \"description\": \"Description\", \"display_image\": \"image.png\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testGetCommunity() throws Exception {
        Community community = new Community(1, 1, "Community 1", "Description", "image.png");

        when(communityService.getCommunity(1)).thenReturn(community);

        mockMvc.perform(MockMvcRequestBuilders.get("/community/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.community_id").value(1));

        verify(communityService, times(1)).getCommunity(1);
    }

    @Test
    public void testGetAllCommunity() throws Exception {
        Community community1 = new Community(1, 1, "Community 1", "Description", "image1.png");
        Community community2 = new Community(2, 1, "Community 2", "Description", "image2.png");
        List<Community> communities = Arrays.asList(community1, community2);

        when(communityService.getAllCommunity()).thenReturn(communities);

        mockMvc.perform(MockMvcRequestBuilders.get("/community")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].community_id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].community_id").value(2));

        verify(communityService, times(1)).getAllCommunity();
    }

    @Test
    public void testGetAllCommunityWithKeyword() throws Exception {
        Community community1 = new Community(1, 1, "Community 1", "Description", "image1.png");
        Community community2 = new Community(2, 1, "Community 2", "Description", "image2.png");
        List<Community> communities = Arrays.asList(community1, community2);

        when(communityService.getAllCommunity("keyword")).thenReturn(communities);

        mockMvc.perform(MockMvcRequestBuilders.get("/community?keyword=keyword")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].community_id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].community_id").value(2));

        verify(communityService, times(1)).getAllCommunity("keyword");
    }

    @Test
    public void testUpdateCommunity() throws Exception {
        Community community = new Community(1, 1, "Community 1", "Description", "image.png");

        when(communityService.updateCommunity(community)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put("/community")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"community_id\": 1, \"created_by\": 1, \"name\": \"Community 1\", \"description\": \"Description\", \"display_image\": \"image.png\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    public void testDeleteCommunity() throws Exception {
        when(communityService.deleteCommunity(1)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/community/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));

        verify(communityService, times(1)).deleteCommunity(1);
    }
}
