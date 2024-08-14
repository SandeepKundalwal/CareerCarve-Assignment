package com.iit.placement.careercarve.assignment.scheduler.repositories;

import com.iit.placement.careercarve.assignment.scheduler.domain.entities.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
}
