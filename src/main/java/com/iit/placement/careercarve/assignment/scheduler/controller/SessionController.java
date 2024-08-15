package com.iit.placement.careercarve.assignment.scheduler.controller;

import com.iit.placement.careercarve.assignment.scheduler.domain.entities.DayOfWeek;
import com.iit.placement.careercarve.assignment.scheduler.domain.entities.SessionEntity;
import com.iit.placement.careercarve.assignment.scheduler.domain.entities.SessionStatus;
import com.iit.placement.careercarve.assignment.scheduler.models.ResponseData;
import com.iit.placement.careercarve.assignment.scheduler.services.SessionService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/session")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }


    @PostMapping(path = "/create")
    public ResponseData createSession(@RequestParam Long mentorId, @RequestParam Long studentId, @RequestParam LocalDateTime sessionStartTime,  @RequestParam long duration, @RequestParam Long areaOfInterestId){
        return sessionService.createNewSession(mentorId, studentId, sessionStartTime, duration, areaOfInterestId);
    }

    @GetMapping(path = "/student/{studentId}/previous_sessions")
    public ResponseData getPreviousSessionsForStudents(@PathVariable Long studentId){
        return sessionService.previousSessionsOfStudent(studentId);
    }

    @GetMapping(path = "/student/{studentId}/upcoming_sessions")
    public ResponseData getUpcomingSessionsForStudents(@PathVariable Long studentId){
        return sessionService.upcomingSessionsOfStudent(studentId);
    }

    @GetMapping(path = "/mentor/{mentorId}/previous_sessions")
    public ResponseData getPreviousSessionsForMentor(@PathVariable Long mentorId){
        return sessionService.previousSessionsOfMentor(mentorId);
    }

    @GetMapping(path = "/mentor/{mentorId}/upcoming_sessions")
    public ResponseData getUpcomingSessionsForMentor(@PathVariable Long mentorId){
        return sessionService.upcomingSessionsOfMentor(mentorId);
    }

    @PatchMapping("/{sessionId}/status")
    public ResponseData updateSessionStatus(@PathVariable Long sessionId, @RequestParam SessionStatus status) {
        return sessionService.updateSessionStatus(sessionId, status);
    }

    @GetMapping("/area-of-interest/{areaOfInterestId}/all-sessions")
    public ResponseData findSessionsBasedOnAreaOfInterest(@PathVariable Long areaOfInterestId, @RequestParam DayOfWeek dayOfWeek){
        return sessionService.findSessionsBasedOnAreaOfInterest(areaOfInterestId, dayOfWeek);
    }
}
