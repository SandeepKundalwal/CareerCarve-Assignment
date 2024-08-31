package com.iit.placement.careercarve.assignment.scheduler.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "area_of_interest")
public class AreaOfInterestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_of_interest_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "areasOfInterestEntity")
    @JsonIgnore
    private List<MentorEntity> mentorEntities;

    @OneToMany(mappedBy = "areaOfInterestEntity")
    @JsonIgnore
    private List<SessionEntity> sessionEntities;

    public AreaOfInterestEntity() {
    }

    public AreaOfInterestEntity(Long id, String name, String description, List<MentorEntity> mentorEntities, List<SessionEntity> sessionEntities) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.mentorEntities = mentorEntities;
        this.sessionEntities = sessionEntities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MentorEntity> getMentorEntities() {
        return mentorEntities;
    }

    public void setMentorEntities(List<MentorEntity> mentorEntities) {
        this.mentorEntities = mentorEntities;
    }

    public List<SessionEntity> getSessions() {
        return sessionEntities;
    }

    public void setSessions(List<SessionEntity> sessionEntities) {
        this.sessionEntities = sessionEntities;
    }
}
