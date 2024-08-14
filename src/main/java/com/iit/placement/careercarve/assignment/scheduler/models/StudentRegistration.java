package com.iit.placement.careercarve.assignment.scheduler.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentRegistration {

    @JsonProperty("name")
    private String studentName;

    @JsonProperty("email")
    private String studentEmail;

    @JsonProperty("contact")
    private String studentContact;

    @JsonProperty("college_name")
    private String studentCollegeName;
}
