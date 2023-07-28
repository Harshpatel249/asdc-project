package com.group6.commune.Controller;

import com.group6.commune.AppLogger.AppLogger;
import com.group6.commune.Enums.UserRoles;
import com.group6.commune.Exceptions.DataNotFoundException;
import com.group6.commune.Exceptions.UnauthorizedAccessException;
import com.group6.commune.Exceptions.ValidationException;
import com.group6.commune.Model.Member;
import com.group6.commune.Model.MemberResponse;
import com.group6.commune.Service.IMemberService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDataNotFoundException(DataNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(ValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", ex.getMessage(), "errors", ex.getErrors()));
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<Map<String, Object>> handleUnauthorizedAccessException(UnauthorizedAccessException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", ex.getMessage()));
    }
}
