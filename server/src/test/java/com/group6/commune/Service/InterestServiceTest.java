package com.group6.commune.Service;

import com.group6.commune.Model.Interest;
import com.group6.commune.Repository.IInterestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InterestServiceTest {
    @Mock
    private IInterestRepository interestRepository;

    @Autowired
    @InjectMocks
    private InterestService interestService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getInterestList() {
//        Interest interest1 = new Interest(1, "Music", "Music");
//        Interest interest2 = new Interest(2, "Dance", "Dance");
//        List<Interest> expectedCommunities = Arrays.asList(interest1, interest2);
//
//        when(interestRepository.getInterestList()).thenReturn(expectedCommunities);
//
//        List<Interest> result = interestService.getInterestList();
//
//        assertEquals(expectedCommunities, result);
//        verify(interestRepository, times(1)).getInterestList();

    }

    @Test
    void addUserInterest() {
    }
}