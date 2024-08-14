package com.iit.placement.careercarve.assignment.scheduler.services;

import com.iit.placement.careercarve.assignment.scheduler.domain.entities.StudentEntity;
import com.iit.placement.careercarve.assignment.scheduler.models.*;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {

    public ResponseData save(StudentRegistration student);

    Boolean isExists(Long id);

//    StudentEntity partialUpdate(Long id, StudentUpdate student);

    public ResponseData changePassword(StudentPasswordChange studentPasswordChange);

    void delete(Long id);

    ResponseData login(StudentLogin studentInfo);

    ResponseData update(StudentUpdate studentInfo);

}
