package com.group6.commune.ServiceTests;
import com.group6.commune.Model.Interest;
import com.group6.commune.Repository.IInterestRepository;
import com.group6.commune.Repository.InterestRepositoryImpl;

import com.group6.commune.Validators.Interestvalidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InterestServiceImplTest {
    @Mock
    private IInterestRepository interestRepository;

    @Mock
    private Interestvalidator interestvalidator;

    @InjectMocks
    private InterestRepositoryImpl interestService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getInterestList() {
//        when(interestRepository.getInterestList()).thenReturn(Arrays.asList(new Interest(), new Interest()));
//
//        assertEquals(2, interestService.getInterestList().size());
    }

    @Test
    void addUserInterest() {

    }
}