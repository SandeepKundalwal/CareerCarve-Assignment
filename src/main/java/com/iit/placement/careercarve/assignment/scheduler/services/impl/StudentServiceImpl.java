package com.iit.placement.careercarve.assignment.scheduler.services.impl;

import com.iit.placement.careercarve.assignment.scheduler.domain.entities.StudentEntity;
import com.iit.placement.careercarve.assignment.scheduler.models.StudentPasswordChange;
import com.iit.placement.careercarve.assignment.scheduler.repositories.StudentRepository;
import com.iit.placement.careercarve.assignment.scheduler.services.StudentService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.OptionalInt;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentEntity save(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }

    @Override
    public Boolean isExists(Long id) {
        return studentRepository.existsById(id);
    }

    @Override
    public StudentEntity partialUpdate(Long id, StudentEntity studentEntity) {
        studentEntity.setId(id);
        return studentRepository.findById(id).map(existingStudent -> {
            Optional.ofNullable(studentEntity.getName()).ifPresent(existingStudent::setName);
            Optional.ofNullable(studentEntity.getEmail()).ifPresent(existingStudent::setEmail);
            Optional.ofNullable(studentEntity.getCollegeName()).ifPresent(existingStudent::setCollegeName);
            Optional.ofNullable(studentEntity.getPassword()).ifPresent(existingStudent::setMobileNo);
            Optional.ofNullable(studentEntity.getAreaOfInterest()).ifPresent(existingStudent::setAreaOfInterest);
            return studentRepository.save(existingStudent);
        }).orElseThrow(() -> new RuntimeException("Student does not exist."));
    }

    @Override
    public StudentEntity changePassword(Long id, StudentPasswordChange studentPasswordChange){
        String oldPassword = studentPasswordChange.getPassword();
        String newPassword = studentPasswordChange.getNewPassword();

        return null;
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
