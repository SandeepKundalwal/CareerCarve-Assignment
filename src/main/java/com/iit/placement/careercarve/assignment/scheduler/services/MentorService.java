package com.iit.placement.careercarve.assignment.scheduler.services;

import com.iit.placement.careercarve.assignment.scheduler.domain.entities.MentorEntity;
import com.iit.placement.careercarve.assignment.scheduler.models.*;
import org.springframework.stereotype.Service;


public interface MentorService {

    public ResponseData save(UserRegistration userInfo);

    void delete(Long id);

    ResponseData login(UserLogin mentorInfo);

    ResponseData changePassword(UserPasswordChange mentorInfo);

    ResponseData update(MentorUpdate mentorInfo);
}
