package com.group6.commune.Service;

import com.group6.commune.Model.EventMembers;
import com.group6.commune.Repository.EventMemberRepositoryImpl;
import com.group6.commune.Repository.EventRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventMemberServiceImpl implements EventMemberService {

    @Autowired
    EventMemberRepositoryImpl eventMemberRepository;

    @Override
    public EventMembers addMember(EventMembers eventMembers) {
        return eventMemberRepository.addMember(eventMembers);
    }

    @Override
    public List<EventMembers> getAllMembers(int eventId) {
        return eventMemberRepository.getAllMembers(eventId);
    }

    @Override
    public EventMembers deleteMember(EventMembers eventMembers) {
        return eventMemberRepository.deleteMember(eventMembers);
    }
}
