package com.iit.placement.careercarve.assignment.scheduler.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity studentEntity;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private MentorEntity mentorEntity;

    @ManyToOne
    @JoinColumn(name = "area_of_interest_id")
    private AreaOfInterest areaOfInterest;

    @Column(name = "session_start_time")
    private java.time.LocalDateTime sessionStartTime;

    @Column(name = "session_end_time")
    private java.time.LocalDateTime sessionEndTime;

    @Column(name = "duration")
    private int duration;

    @Enumerated(EnumType.STRING)
    private SessionStatus status;

    public Session() {
    }

    public Session(Long id, StudentEntity studentEntity, MentorEntity mentorEntity, AreaOfInterest areaOfInterest, LocalDateTime sessionStartTime, LocalDateTime sessionEndTime, int duration, SessionStatus status) {
        this.id = id;
        this.studentEntity = studentEntity;
        this.mentorEntity = mentorEntity;
        this.areaOfInterest = areaOfInterest;
        this.sessionStartTime = sessionStartTime;
        this.sessionEndTime = sessionEndTime;
        this.duration = duration;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    public MentorEntity getMentorEntity() {
        return mentorEntity;
    }

    public void setMentorEntity(MentorEntity mentorEntity) {
        this.mentorEntity = mentorEntity;
    }

    public AreaOfInterest getAreaOfInterest() {
        return areaOfInterest;
    }

    public void setAreaOfInterest(AreaOfInterest areaOfInterest) {
        this.areaOfInterest = areaOfInterest;
    }

    public LocalDateTime getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(LocalDateTime sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public LocalDateTime getSessionEndTime() {
        return sessionEndTime;
    }

    public void setSessionEndTime(LocalDateTime sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }
}
