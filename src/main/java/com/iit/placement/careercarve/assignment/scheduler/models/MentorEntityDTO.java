package com.iit.placement.careercarve.assignment.scheduler.models;

import com.iit.placement.careercarve.assignment.scheduler.domain.entities.AvailabilityEntity;

import java.util.List;

public class MentorEntityDTO {
    private Long id;
    private String name;
    private String email;
    private String mobileNo;
    private String companyName;
    private String jobTitle;

    public MentorEntityDTO() {
    }

    public MentorEntityDTO(Long id, String name, String email, String mobileNo, String companyName, String jobTitle) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;
        this.companyName = companyName;
        this.jobTitle = jobTitle;
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
}
