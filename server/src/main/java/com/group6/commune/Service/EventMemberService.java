package com.group6.commune.Service;

import com.group6.commune.Model.EventMembers;

import java.util.List;

public interface EventMemberService {
    EventMembers addMember(EventMembers eventMembers);
    List<EventMembers> getAllMembers(int eventId);
    EventMembers deleteMember(EventMembers eventMembers);
}
