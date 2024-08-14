package com.iit.placement.careercarve.assignment.scheduler.services;

import com.iit.placement.careercarve.assignment.scheduler.domain.entities.StudentEntity;
import com.iit.placement.careercarve.assignment.scheduler.models.StudentPasswordChange;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {

    public StudentEntity save(StudentEntity studentEntity);

    Boolean isExists(Long id);

    StudentEntity partialUpdate(Long id, StudentEntity studentEntity);

    public StudentEntity changePassword(Long id, StudentPasswordChange studentPasswordChange);

    void delete(Long id);
}
