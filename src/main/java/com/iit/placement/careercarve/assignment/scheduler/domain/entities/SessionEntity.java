package com.iit.placement.careercarve.assignment.scheduler.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private StudentEntity studentEntity;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    @JsonIgnore
    private MentorEntity mentorEntity;

    @ManyToOne
    @JoinColumn(name = "area_of_interest_id")
    @JsonIgnore
    private AreaOfInterestEntity areaOfInterestEntity;

    @Column(name = "session_start_time")
    private LocalDateTime sessionStartTime;

    @Column(name = "duration")
    private long duration;

    @Enumerated(EnumType.STRING)
    private SessionStatus status;

    @Column(name = "isPremium")
    private Boolean isPremium;

    public SessionEntity() {
    }

    public SessionEntity(Long id, StudentEntity studentEntity, MentorEntity mentorEntity, AreaOfInterestEntity areaOfInterestEntity, LocalDateTime sessionStartTime, long duration, SessionStatus status, Boolean isPremium) {
        this.id = id;
        this.studentEntity = studentEntity;
        this.mentorEntity = mentorEntity;
        this.areaOfInterestEntity = areaOfInterestEntity;
        this.sessionStartTime = sessionStartTime;
        this.duration = duration;
        this.status = status;
        this.isPremium = isPremium;
    }

    public AreaOfInterestEntity getAreaOfInterestEntity() {
        return areaOfInterestEntity;
    }

    public void setAreaOfInterestEntity(AreaOfInterestEntity areaOfInterestEntity) {
        this.areaOfInterestEntity = areaOfInterestEntity;
    }

    public Boolean getPremium() {
        return isPremium;
    }

    public void setPremium(Boolean premium) {
        isPremium = premium;
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
