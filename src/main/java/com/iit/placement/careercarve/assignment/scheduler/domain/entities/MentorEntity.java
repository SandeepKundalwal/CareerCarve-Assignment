package com.iit.placement.careercarve.assignment.scheduler.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "mentor")
public class MentorEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mentor_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotNull
    private String password;

    @Column(name = "mobile_number")
    private String mobileNo;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "job_title")
    private String jobTitle;

    @OneToMany(mappedBy = "mentorEntity")
    @JsonIgnore
    private List<Availability> availabilities;

    @ManyToMany
    @JoinTable(
            name = "mentor_expertise_mapping",
            joinColumns = @JoinColumn(name = "mentor_id"),
            inverseJoinColumns = @JoinColumn(name = "area_of_interest_id")
    )
    @JsonIgnore
    private List<AreaOfInterest> areasOfInterest;

    @OneToMany(mappedBy = "mentorEntity")
    @JsonIgnore
    private List<Session> sessions;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private java.util.Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date updatedAt;

    public MentorEntity() {
    }

    public MentorEntity(Long id, String name, String email, String password, String mobileNo, String companyName, String jobTitle, List<Availability> availabilities, List<AreaOfInterest> areasOfInterest, List<Session> sessions, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobileNo = mobileNo;
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.availabilities = availabilities;
        this.areasOfInterest = areasOfInterest;
        this.sessions = sessions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public List<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<Availability> availabilities) {
        this.availabilities = availabilities;
    }

    public List<AreaOfInterest> getAreasOfInterest() {
        return areasOfInterest;
    }

    public void setAreasOfInterest(List<AreaOfInterest> areasOfInterest) {
        this.areasOfInterest = areasOfInterest;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new java.util.Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new java.util.Date();
    }
}
