package com.iit.placement.careercarve.assignment.scheduler.models;

import com.iit.placement.careercarve.assignment.scheduler.domain.entities.DayOfWeek;

import java.time.LocalTime;

public class AvailabilityEntityDTO {
    private Long id;
    private DayOfWeek dayOfWeek;
    private java.time.LocalTime availableFrom;
    private java.time.LocalTime availableTo;

    public AvailabilityEntityDTO() {
    }

    public AvailabilityEntityDTO(Long id, DayOfWeek dayOfWeek, LocalTime availableFrom, LocalTime availableTo) {
        this.id = id;
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
