package com.iit.placement.careercarve.assignment.scheduler.controller;

import com.iit.placement.careercarve.assignment.scheduler.models.*;
import com.iit.placement.careercarve.assignment.scheduler.services.MentorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mentor")
public class MentorController {

    private final MentorService mentorService;

    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @PostMapping(path = "/login")
    public ResponseData mentorLogin(@RequestBody UserLogin userInfo){
        return mentorService.login(userInfo);
    }

    @PostMapping(path = "/registration")
    public ResponseData createMentor(@RequestBody UserRegistration userInfo){
        return mentorService.save(userInfo);
    }

    @PostMapping(path = "/change-password")
    public ResponseData changePassword(@RequestBody UserPasswordChange userInfo){
        return mentorService.changePassword(userInfo);
    }

    @PostMapping(path = "/update")
    public ResponseData mentorUpdate(@RequestBody MentorUpdate mentorInfo){
        return mentorService.update(mentorInfo);
    }
}
