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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InterestServiceImplTest {
    @Mock
    private IInterestRepository interestRepository;

    @Autowired
    @InjectMocks
    private InterestServiceImpl interestService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getInterestList() {
        Interest interest1 = new Interest(1, "Art", "Art");
        Interest interest2 = new Interest(2, "Music", "Music");
        List<Interest> expectedInterests = Arrays.asList(interest1, interest2);

        when(interestRepository.getInterestList()).thenReturn(expectedInterests);

        List<Interest> result = interestService.getInterestList();

        assertEquals(expectedInterests, result);
        verify(interestRepository, times(2)).getInterestList();
    }

    @Test
    void addUserInterest() {
    }
}