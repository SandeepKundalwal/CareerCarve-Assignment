package com.iit.placement.careercarve.assignment.scheduler.models;

import com.iit.placement.careercarve.assignment.scheduler.domain.entities.SessionStatus;

import java.time.LocalDateTime;

public class MentorSessionDTO {
    private Long id;
    private StudentEntityDTO studentEntityDTO;
    private AreaOfInterestEntityDTO areaOfInterestEntityDTO;
    private LocalDateTime sessionStartTime;
    private long duration;
    private SessionStatus status;

    public MentorSessionDTO() {
    }

    public MentorSessionDTO(Long id, StudentEntityDTO studentEntityDTO, AreaOfInterestEntityDTO areaOfInterestEntityDTO, LocalDateTime sessionStartTime, long duration, SessionStatus status) {
        this.id = id;
        this.studentEntityDTO = studentEntityDTO;
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

    public StudentEntityDTO getStudentEntityDTO() {
        return studentEntityDTO;
    }

    public void setStudentEntityDTO(StudentEntityDTO studentEntityDTO) {
        this.studentEntityDTO = studentEntityDTO;
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
