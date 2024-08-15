package com.iit.placement.careercarve.assignment.scheduler.models;

import java.time.LocalDateTime;

public class MentorAvailability {
    private Long mentorId;
    private LocalDateTime lastestAvailableSessionTime;

    public MentorAvailability() {
    }

    public MentorAvailability(Long mentorId, LocalDateTime lastestAvailableSessionTime) {
        this.mentorId = mentorId;
        this.lastestAvailableSessionTime = lastestAvailableSessionTime;
    }

    public Long getMentorId() {
        return mentorId;
    }

    public void setMentorId(Long mentorId) {
        this.mentorId = mentorId;
    }

    public LocalDateTime getLastestAvailableSessionTime() {
        return lastestAvailableSessionTime;
    }

    public void setLastestAvailableSessionTime(LocalDateTime lastestAvailableSessionTime) {
        this.lastestAvailableSessionTime = lastestAvailableSessionTime;
    }
}
