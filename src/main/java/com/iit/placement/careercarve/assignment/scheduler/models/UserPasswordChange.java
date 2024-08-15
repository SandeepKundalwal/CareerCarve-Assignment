package com.iit.placement.careercarve.assignment.scheduler.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class UserPasswordChange {
    @JsonProperty("username")
    private String username;

    @JsonProperty("old_password")
    private String oldPassword;

    @JsonProperty("new_password")
    private String newPassword;

    public UserPasswordChange() {
    }

    public UserPasswordChange(String username, String oldPassword, String newPassword) {
        this.username = username;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getUserName(){
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
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
