package com.group6.commune.Controller;

import com.group6.commune.AppLogger.AppLogger;
import com.group6.commune.Enums.UserRoles;
import com.group6.commune.Model.Member;
import com.group6.commune.Model.MemberResponse;
import com.group6.commune.Service.IMemberService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community/{community_id}/members")
public class MemberController {
    @Autowired
    @Qualifier("MemberService")
    private IMemberService memberService;

    Logger logger = AppLogger.getLogger();

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public Boolean addMember(@RequestBody Member member){
        logger.info("POST req on /community/{community_id}/members");
        return memberService.addMember(member);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<MemberResponse> getAllMember(@PathVariable int community_id){
        logger.info("GET req on /community/community_id/members");
        return memberService.getAllMembers(community_id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping
    public Boolean deleteMember(@RequestBody Member member){
        logger.info("DELETE req on /community/community_id/members");
        return memberService.deleteMember(member);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping
    public Boolean changeUserRole(@RequestBody Member member,@RequestParam(name = "new_role") String new_role){
        logger.info("PUT req on /community/community_id/members");
        UserRoles newRole = new_role.equalsIgnoreCase(UserRoles.Member.toString())?UserRoles.Member:UserRoles.Admin;
        return memberService.changeUserRole(member, newRole);
    }
}
