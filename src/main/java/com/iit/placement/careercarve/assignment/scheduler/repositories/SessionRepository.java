package com.iit.placement.careercarve.assignment.scheduler.repositories;

import com.iit.placement.careercarve.assignment.scheduler.domain.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
    @Query("SELECT s FROM Session s WHERE s.sessionStartTime < :currentDateTime AND s.studentEntity.id = :studentId")
    List<SessionEntity> findSessionBeforeCurrentTimeAndById(LocalDateTime currentDateTime, Long id);

    @Query("SELECT s FROM Session s WHERE s.sessionStartTime > :currentDateTime AND s.studentEntity.id = :studentId")
    List<SessionEntity> findSessionAfterCurrentTimeAndById(LocalDateTime currentDateTime, Long id);
}
