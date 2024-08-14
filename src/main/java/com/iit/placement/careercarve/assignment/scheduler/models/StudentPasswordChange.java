package com.iit.placement.careercarve.assignment.scheduler.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class StudentPasswordChange {
    @JsonProperty("student_id")
    private String studentId;

    @JsonProperty("old_password")
    private String oldPassword;

    @JsonProperty("new_password")
    private String newPassword;

    public StudentPasswordChange() {
    }

    public StudentPasswordChange(String studentId, String oldPassword, String newPassword) {
        this.studentId = studentId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }



    public void setUserId(String userId) {
        this.studentId = userId;
    }

    public String getPassword() {
        return oldPassword;
    }

    public void setPassword(String password) {
        this.oldPassword = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPass) {
        this.newPassword = BCrypt.hashpw(newPass, BCrypt.gensalt());
    }
}
