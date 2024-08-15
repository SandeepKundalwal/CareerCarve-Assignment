package com.iit.placement.careercarve.assignment.scheduler.services;

import com.iit.placement.careercarve.assignment.scheduler.domain.entities.DayOfWeek;
import com.iit.placement.careercarve.assignment.scheduler.domain.entities.SessionStatus;
import com.iit.placement.careercarve.assignment.scheduler.models.ResponseData;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

public interface SessionService {

    ResponseData createNewSession(Long mentorId, Long studentId, LocalDateTime startTime, long duration, Long areaOfInterestId);

    ResponseData previousSessionsOfStudent(Long studentId);

    ResponseData upcomingSessionsOfStudent(Long studentId);

    ResponseData previousSessionsOfMentor(Long mentorId);

    ResponseData upcomingSessionsOfMentor(Long mentorId);

    ResponseData updateSessionStatus(Long sessionId, SessionStatus status);

    ResponseData findSessionsBasedOnAreaOfInterest(Long areaOfInterestId, DayOfWeek dayOfWeek);
}
