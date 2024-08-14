package com.iit.placement.careercarve.assignment.scheduler.controller;


import com.iit.placement.careercarve.assignment.scheduler.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class StudentController {

//    private StudentService studentService;
//
//    private Mapper<StudentEntity, StudentDTO> studentMapper;
//
//    public StudentController(StudentService studentService, Mapper<StudentEntity, StudentDTO> studentMapper){
//        this.studentService = studentService;
//        this.studentMapper = studentMapper;
//    }
//
//
//    @PostMapping(path = "/registration")
//    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO){
//        StudentEntity studentEntity = studentMapper.mapFrom(studentDTO);
//        StudentEntity savedStudentEntity = studentService.save(studentEntity);
//        return new ResponseEntity<>(studentMapper.mapTo(savedStudentEntity), HttpStatus.CREATED);
//    }
}
