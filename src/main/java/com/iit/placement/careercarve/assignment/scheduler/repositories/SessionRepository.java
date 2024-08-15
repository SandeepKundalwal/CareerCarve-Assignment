package com.iit.placement.careercarve.assignment.scheduler.repositories;

import com.iit.placement.careercarve.assignment.scheduler.domain.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {

    @Query(value = "SELECT * FROM session s WHERE s.session_start_time < :currentDateTime AND s.student_id = :studentId", nativeQuery = true)
    List<SessionEntity> findSessionBeforeCurrentTimeAndByIdForStudent(@Param("currentDateTime") LocalDateTime currentDateTime, @Param("studentId") Long studentId);

    @Query(value = "SELECT * FROM session s WHERE s.session_start_time > :currentDateTime AND s.student_id = :studentId", nativeQuery = true)
    List<SessionEntity> findSessionAfterCurrentTimeAndByIdForStudent(@Param("currentDateTime") LocalDateTime currentDateTime, @Param("studentId") Long studentId);

    @Query(value = "SELECT * FROM session s WHERE s.session_start_time < :currentDateTime AND s.mentor_id = :mentorId", nativeQuery = true)
    List<SessionEntity> findSessionBeforeCurrentTimeAndByIdForMentor(@Param("currentDateTime") LocalDateTime currentDateTime, @Param("mentorId") Long mentorId);

    @Query(value = "SELECT * FROM session s WHERE s.session_start_time > :currentDateTime AND s.mentor_id = :mentorId", nativeQuery = true)
    List<SessionEntity> findSessionAfterCurrentTimeAndByIdForMentor(@Param("currentDateTime") LocalDateTime currentDateTime, @Param("mentorId") Long mentorId);
}
