package com.iit.placement.careercarve.assignment.scheduler.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iit.placement.careercarve.assignment.scheduler.domain.entities.AreaOfInterestEntity;
import com.iit.placement.careercarve.assignment.scheduler.domain.entities.AvailabilityEntity;

import java.util.List;

public class MentorUpdate {

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("mobile_no")
    private String mobileNo;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("job_title")
    private String jobTitle;

    @JsonProperty("availabilities")
    private List<AvailabilityEntity> availabilities;

    @JsonProperty("areas_of_interest")
    private List<Long> areasOfInterest;

    public MentorUpdate() {
    }

    public MentorUpdate(String name,
                        String email,
                        String mobileNo,
                        String companyName,
                        String jobTitle,
                        List<AvailabilityEntity> availabilities,
                        List<Long> areasOfInterest) {
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.availabilities = availabilities;
        this.areasOfInterest = areasOfInterest;
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

    public List<AvailabilityEntity> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<AvailabilityEntity> availabilities) {
        this.availabilities = availabilities;
    }

    public List<Long> getAreasOfInterest() {
        return areasOfInterest;
    }

    public void setAreasOfInterest(List<Long> areasOfInterest) {
        this.areasOfInterest = areasOfInterest;
    }
}
