package com.iit.placement.careercarve.assignment.scheduler.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "name")
    private String name;


    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotNull
    private String password;

    @Column(name = "mobile_number")
    private String mobileNo;

    @Column(name = "college_name")
    private String collegeName;

    @ManyToOne
    @JoinColumn(name = "area_of_interest_id")
    private AreaOfInterest areaOfInterest;

    @OneToMany(mappedBy = "studentEntity")
    @JsonIgnore
    private List<Session> sessions;

    @Column(name="date_created", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false, updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdAt;

    @Column(name="date_modified", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable=false, updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date updatedAt;

    public StudentEntity() {
    }

    public StudentEntity(String name, Long id, String email, String password, String mobileNo, String collegeName, AreaOfInterest areaOfInterest, List<Session> sessions, Date createdAt, Date updatedAt) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.password = password;
        this.mobileNo = mobileNo;
        this.collegeName = collegeName;
        this.areaOfInterest = areaOfInterest;
        this.sessions = sessions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new java.util.Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new java.util.Date();
    }

    protected void setPassword(String password){
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
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

    public String getPassword() {
        return password;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public AreaOfInterest getAreaOfInterest() {
        return areaOfInterest;
    }

    public void setAreaOfInterest(AreaOfInterest areaOfInterest) {
        this.areaOfInterest = areaOfInterest;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
