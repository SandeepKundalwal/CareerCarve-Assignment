package com.iit.placement.careercarve.assignment.scheduler.models;

public class StudentEntityDTO {
    private Long id;
    private String name;
    private String email;
    private String mobileNo;
    private String collegeName;

    public StudentEntityDTO() {
    }

    public StudentEntityDTO(Long id, String name, String email, String mobileNo, String collegeName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;
        this.collegeName = collegeName;
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

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
