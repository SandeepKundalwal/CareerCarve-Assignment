package com.iit.placement.careercarve.assignment.scheduler.controller;


import com.iit.placement.careercarve.assignment.scheduler.models.*;
import com.iit.placement.careercarve.assignment.scheduler.services.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping(path = "/login")
    public ResponseData studentLogin(@RequestBody UserLogin studentInfo){
        return studentService.login(studentInfo);
    }


    @PostMapping(path = "/registration")
    public ResponseData createStudent(@RequestBody UserRegistration studentInfo){
        return studentService.save(studentInfo);
    }

    @PostMapping(path = "/change-password")
    public ResponseData changePassword(@RequestBody UserPasswordChange studentInfo){
        return studentService.changePassword(studentInfo);
    }

    @PostMapping(path = "/update")
    public ResponseData update(@RequestBody StudentUpdate studentInfo){
        return studentService.update(studentInfo);
    }

    @GetMapping(path = "/find-mentor")
    public ResponseData findMentorByAreaOfInterest(@PathVariable Long areaOfInterestId){
        return null;
    }
}
