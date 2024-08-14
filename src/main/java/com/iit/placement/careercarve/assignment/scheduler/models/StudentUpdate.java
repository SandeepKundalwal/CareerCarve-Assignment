package com.iit.placement.careercarve.assignment.scheduler.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iit.placement.careercarve.assignment.scheduler.domain.entities.AreaOfInterestEntity;

public class StudentUpdate {

    @JsonProperty("name")
    private String name;

    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String email;

    @JsonProperty("mobile_no")
    private String mobileNo;

    @JsonProperty("college_name")
    private String collegeName;

    @JsonProperty("area_of_interest")
    private AreaOfInterestEntity areaOfInterestEntity;

    public StudentUpdate() {
    }

    public StudentUpdate(String name, String password, String email, String mobileNo, String collegeName, AreaOfInterestEntity areaOfInterestEntity) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.mobileNo = mobileNo;
        this.collegeName = collegeName;
        this.areaOfInterestEntity = areaOfInterestEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public AreaOfInterestEntity getAreaOfInterest() {
        return areaOfInterestEntity;
    }

    public void setAreaOfInterest(AreaOfInterestEntity areaOfInterestEntity) {
        this.areaOfInterestEntity = areaOfInterestEntity;
    }
}
