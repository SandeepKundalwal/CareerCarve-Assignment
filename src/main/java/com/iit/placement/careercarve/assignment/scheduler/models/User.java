package com.iit.placement.careercarve.assignment.scheduler.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty("user_id")
    Long userId;

    @JsonProperty("user_name")
    String userName;

    @JsonProperty("password")
    private String password;
    @JsonProperty("mobile_num")
    private String mobileNum;

    @JsonProperty("user_type")
    private String userType;

    public User() {
    }

    public User(Long userId, String userName, String mobileNum, String userType) {
        this.userId = userId;
        this.userName = userName;
        this.mobileNum = mobileNum;
        this.userType = userType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
