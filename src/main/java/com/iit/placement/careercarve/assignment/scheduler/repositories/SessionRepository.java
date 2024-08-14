package com.iit.placement.careercarve.assignment.scheduler.repositories;

import com.iit.placement.careercarve.assignment.scheduler.domain.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}
