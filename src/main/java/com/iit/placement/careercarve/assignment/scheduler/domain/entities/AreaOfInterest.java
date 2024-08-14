package com.iit.placement.careercarve.assignment.scheduler.domain.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "area_of_interest")
public class AreaOfInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_of_interest_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "areaOfInterest")
    private List<StudentEntity> studentEntities;

    @ManyToMany(mappedBy = "areasOfInterest")
    private List<MentorEntity> mentorEntities;

    @OneToMany(mappedBy = "areaOfInterest")
    private List<Session> sessions;

    public AreaOfInterest() {
    }

    public AreaOfInterest(Long id, String name, String description, List<StudentEntity> studentEntities, List<MentorEntity> mentorEntities, List<Session> sessions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.studentEntities = studentEntities;
        this.mentorEntities = mentorEntities;
        this.sessions = sessions;
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

    public List<StudentEntity> getStudentEntities() {
        return studentEntities;
    }

    public void setStudentEntities(List<StudentEntity> studentEntities) {
        this.studentEntities = studentEntities;
    }

    public List<MentorEntity> getMentorEntities() {
        return mentorEntities;
    }

    public void setMentorEntities(List<MentorEntity> mentorEntities) {
        this.mentorEntities = mentorEntities;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
