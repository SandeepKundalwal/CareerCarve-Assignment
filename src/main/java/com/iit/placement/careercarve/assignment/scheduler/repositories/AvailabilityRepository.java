package com.iit.placement.careercarve.assignment.scheduler.repositories;

import com.iit.placement.careercarve.assignment.scheduler.domain.entities.AvailabilityEntity;
import com.iit.placement.careercarve.assignment.scheduler.domain.entities.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<AvailabilityEntity, Long> {
//    List<AvailabilityEntity> findByMentorIdAndDayOfWeek(Long id, DayOfWeek dayOfWeek);
}
