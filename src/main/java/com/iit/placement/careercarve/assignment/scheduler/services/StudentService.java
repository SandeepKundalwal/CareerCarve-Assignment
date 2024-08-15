package com.iit.placement.careercarve.assignment.scheduler.services;

import com.iit.placement.careercarve.assignment.scheduler.models.*;
import org.springframework.stereotype.Service;

public interface StudentService {

    public ResponseData save(UserRegistration student);

    public ResponseData changePassword(UserPasswordChange userPasswordChange);

    void delete(Long id);

    ResponseData login(UserLogin studentInfo);

    ResponseData update(StudentUpdate studentInfo);

}
