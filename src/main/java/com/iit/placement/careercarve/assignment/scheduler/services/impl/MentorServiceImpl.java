package com.iit.placement.careercarve.assignment.scheduler.services.impl;

import com.iit.placement.careercarve.assignment.scheduler.domain.entities.MentorEntity;
import com.iit.placement.careercarve.assignment.scheduler.repositories.MentorRepository;
import com.iit.placement.careercarve.assignment.scheduler.services.MentorService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MentorServiceImpl implements MentorService {

    private final MentorRepository mentorRepository;

    public MentorServiceImpl(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    @Override
    public MentorEntity save(MentorEntity mentorEntity) {
        return mentorRepository.save(mentorEntity);
    }

    @Override
    public List<MentorEntity> findAll() {
        return StreamSupport.stream(mentorRepository
                .findAll()
                .spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MentorEntity> findOne(Long id) {
        return mentorRepository.findById(id);
    }

    @Override
    public Boolean isExists(Long id) {
        return mentorRepository.existsById(id);
    }

    @Override
    public MentorEntity partialUpdate(Long id, MentorEntity mentorEntity) {
        mentorEntity.setId(id);

        return mentorRepository.findById(id).map(existingMentor -> {
            Optional.ofNullable(mentorEntity.getName()).ifPresent(existingMentor::setName);
            Optional.ofNullable(mentorEntity.getEmail()).ifPresent(existingMentor::setEmail);
            Optional.ofNullable(mentorEntity.getMobileNo()).ifPresent(existingMentor::setMobileNo);
            Optional.ofNullable(mentorEntity.getCompanyName()).ifPresent(existingMentor::setCompanyName);
            Optional.ofNullable(mentorEntity.getJobTitle()).ifPresent(existingMentor::setJobTitle);
            Optional.ofNullable(mentorEntity.getAreasOfInterest()).ifPresent((existingMentor::setAreasOfInterest));
            Optional.ofNullable(mentorEntity.getAvailabilities()).ifPresent(existingMentor::setAvailabilities);
            return mentorRepository.save(existingMentor);
        }).orElseThrow(() -> new RuntimeException("Mentor does not exist."));
    }

    @Override
    public void delete(Long id) {
        mentorRepository.deleteById(id);
    }
}
