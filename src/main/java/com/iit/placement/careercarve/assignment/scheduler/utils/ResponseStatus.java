package com.iit.placement.careercarve.assignment.scheduler.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import javax.xml.bind.annotation.*;

@XmlRootElement (name="status")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"req_id", "http_code", "status_code", "message"})
public class ResponseStatus {
    @JsonProperty("req_id")
    private String reqId;

    @JsonProperty("http_code")
    private HttpStatus httpCode;

    @JsonProperty("status_code")
    private String statusCode;

    @JsonProperty("message")
    private String msg;


    public ResponseStatus() {}


    public ResponseStatus(String reqId, HttpStatus httpCode, String statusCode, String msg) {
        this.reqId = reqId;
        this.httpCode = httpCode;
        this.statusCode = statusCode;
        this.msg = msg;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public HttpStatus getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(HttpStatus httpCode) {
        this.httpCode = httpCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
