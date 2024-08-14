package com.iit.placement.careercarve.assignment.scheduler.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class StudentRegistration {

    @JsonProperty("name")
    private String studentName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String studentEmail;

    @JsonProperty("mobile_no")
    private String studentMobileNo;

    public StudentRegistration() {
    }

    public StudentRegistration(String studentName, String password, String studentEmail, String studentContact) {
        this.studentName = studentName;
        this.password = password;
        this.studentEmail = studentEmail;
        this.studentMobileNo = studentContact;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentMobileNo() {
        return studentMobileNo;
    }

    public void setStudentMobileNo(String studentMobileNo) {
        this.studentMobileNo = studentMobileNo;
    }
}
