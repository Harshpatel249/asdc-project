package com.group6.commune.ServiceTests;

import com.group6.commune.Model.Community;
import com.group6.commune.Repository.ICommunityRepository;
import com.group6.commune.Service.CommunityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CommunityServiceTests {

    @Mock
    private ICommunityRepository communityRepository;

    @Autowired
    @InjectMocks
    private CommunityService communityService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCommunity() {
        Community community = new Community(1, 1, "Community 1", "Description", "image.png");

        when(communityRepository.createCommunity(community)).thenReturn(true);

        boolean result = communityService.createCommunity(community);

        assertEquals(true, result);
        verify(communityRepository, times(1)).createCommunity(community);
    }

    @Test
    public void testGetCommunity() {
        Community expectedCommunity = new Community(1, 1, "Community 1", "Description", "image.png");

        when(communityRepository.getCommunity(1)).thenReturn(expectedCommunity);

        Community result = communityService.getCommunity(1);

        assertEquals(expectedCommunity, result);
        verify(communityRepository, times(1)).getCommunity(1);
    }

    @Test
    public void testUpdateCommunity() {
        Community community = new Community(1, 1, "Community 1", "Description", "image.png");

        when(communityRepository.updateCommunity(community)).thenReturn(true);

        boolean result = communityService.updateCommunity(community);

        assertEquals(true, result);
        verify(communityRepository, times(1)).updateCommunity(community);
    }

    @Test
    public void testDeleteCommunity() {
        when(communityRepository.deleteCommunity(1)).thenReturn(true);

        boolean result = communityService.deleteCommunity(1);

        assertEquals(true, result);
        verify(communityRepository, times(1)).deleteCommunity(1);
    }

    @Test
    public void testGetAllCommunity() {
        Community community1 = new Community(1, 1, "Community 1", "Description", "image1.png");
        Community community2 = new Community(2, 1, "Community 2", "Description", "image2.png");
        List<Community> expectedCommunities = Arrays.asList(community1, community2);

        when(communityRepository.getAllCommunity()).thenReturn(expectedCommunities);

        List<Community> result = communityService.getAllCommunity();

        assertEquals(expectedCommunities, result);
        verify(communityRepository, times(1)).getAllCommunity();
    }

    @Test
    public void testGetAllCommunityWithKeyword() {
        Community community1 = new Community(1, 1, "first community", "Description", "image1.png");
        Community community2 = new Community(2, 1, "second community", "Description", "image2.png");
        List<Community> expectedCommunities = Arrays.asList(community1);

        when(communityRepository.getAllCommunity("first")).thenReturn(expectedCommunities);

        List<Community> result = communityService.getAllCommunity("first");

        assertEquals(expectedCommunities, result);
        verify(communityRepository, times(1)).getAllCommunity("first");
    }
}
