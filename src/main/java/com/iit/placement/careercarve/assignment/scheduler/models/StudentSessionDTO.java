package com.iit.placement.careercarve.assignment.scheduler.models;

import com.iit.placement.careercarve.assignment.scheduler.domain.entities.SessionStatus;

import java.time.LocalDateTime;

public class StudentSessionDTO {
    private Long id;
    private MentorEntityDTO mentorEntityDTO;
    private AreaOfInterestEntityDTO areaOfInterestEntityDTO;
    private LocalDateTime sessionStartTime;
    private long duration;
    private SessionStatus status;

    public StudentSessionDTO() {
    }

    public StudentSessionDTO(Long id, MentorEntityDTO mentorEntityDTO, AreaOfInterestEntityDTO areaOfInterestEntityDTO, LocalDateTime sessionStartTime, LocalDateTime sessionEndTime, long duration, SessionStatus status) {
        this.id = id;
        this.mentorEntityDTO = mentorEntityDTO;
        this.areaOfInterestEntityDTO = areaOfInterestEntityDTO;
        this.sessionStartTime = sessionStartTime;
        this.duration = duration;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MentorEntityDTO getMentorEntityDTO() {
        return mentorEntityDTO;
    }

    public void setMentorEntityDTO(MentorEntityDTO mentorEntityDTO) {
        this.mentorEntityDTO = mentorEntityDTO;
    }

    public AreaOfInterestEntityDTO getAreaOfInterestEntityDTO() {
        return areaOfInterestEntityDTO;
    }

    public void setAreaOfInterestEntityDTO(AreaOfInterestEntityDTO areaOfInterestEntityDTO) {
        this.areaOfInterestEntityDTO = areaOfInterestEntityDTO;
    }

    public LocalDateTime getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(LocalDateTime sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }
}
