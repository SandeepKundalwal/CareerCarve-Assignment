package com.iit.placement.careercarve.assignment.scheduler.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "session")
public class SessionEntity {

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
    private AreaOfInterestEntity areaOfInterestEntity;

    @Column(name = "session_start_time")
    private LocalDateTime sessionStartTime;

    @Column(name = "session_end_time")
    private LocalDateTime sessionEndTime;

    @Column(name = "duration")
    private int duration;

    @Enumerated(EnumType.STRING)
    private SessionStatus status;

    public SessionEntity() {
    }

    public SessionEntity(Long id, StudentEntity studentEntity, MentorEntity mentorEntity, AreaOfInterestEntity areaOfInterestEntity, LocalDateTime sessionStartTime, LocalDateTime sessionEndTime, int duration, SessionStatus status) {
        this.id = id;
        this.studentEntity = studentEntity;
        this.mentorEntity = mentorEntity;
        this.areaOfInterestEntity = areaOfInterestEntity;
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

    public AreaOfInterestEntity getAreaOfInterest() {
        return areaOfInterestEntity;
    }

    public void setAreaOfInterest(AreaOfInterestEntity areaOfInterestEntity) {
        this.areaOfInterestEntity = areaOfInterestEntity;
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
