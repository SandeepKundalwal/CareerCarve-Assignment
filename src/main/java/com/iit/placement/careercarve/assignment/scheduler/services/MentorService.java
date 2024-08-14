package com.iit.placement.careercarve.assignment.scheduler.services;

import com.iit.placement.careercarve.assignment.scheduler.domain.entities.MentorEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MentorService {

    public MentorEntity save(MentorEntity mentorEntity);

    List<MentorEntity> findAll();

    Optional<MentorEntity> findOne(Long id);

    Boolean isExists(Long id);

    MentorEntity partialUpdate(Long id, MentorEntity mentorEntity);

    void delete(Long id);
}
