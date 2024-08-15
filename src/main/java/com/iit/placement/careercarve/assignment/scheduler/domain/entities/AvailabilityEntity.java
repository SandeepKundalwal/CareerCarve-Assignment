package com.iit.placement.careercarve.assignment.scheduler.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "availability")
public class AvailabilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availability_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    @JsonIgnore
    private MentorEntity mentorEntity;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(name = "available_from")
    private java.time.LocalTime availableFrom;

    @Column(name = "available_till")
    private java.time.LocalTime availableTo;

    public AvailabilityEntity() {
    }

    public AvailabilityEntity(Long id, MentorEntity mentorEntity, DayOfWeek dayOfWeek, LocalTime availableFrom, LocalTime availableTo) {
        this.id = id;
        this.mentorEntity = mentorEntity;
        this.dayOfWeek = dayOfWeek;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MentorEntity getMentorEntity() {
        return mentorEntity;
    }

    public void setMentorEntity(MentorEntity mentorEntity) {
        this.mentorEntity = mentorEntity;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(LocalTime availableFrom) {
        this.availableFrom = availableFrom;
    }

    public LocalTime getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(LocalTime availableTo) {
        this.availableTo = availableTo;
    }
}
