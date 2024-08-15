package com.iit.placement.careercarve.assignment.scheduler.repositories;

import com.iit.placement.careercarve.assignment.scheduler.domain.entities.MentorEntity;
import com.iit.placement.careercarve.assignment.scheduler.domain.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<MentorEntity, Long> {
    MentorEntity findMentorByEmail(String email);

    MentorEntity findMentorByMobileNo(String mobileNo);
}
