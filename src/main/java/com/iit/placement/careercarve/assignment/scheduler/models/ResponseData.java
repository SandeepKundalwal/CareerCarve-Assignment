package com.iit.placement.careercarve.assignment.scheduler.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;
import java.util.Map;

@XmlRootElement (name="status")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"req_id", "http_code", "status_code", "message"})
public class ResponseData {
    //    @XmlElement(name = "req_id")
    @JsonProperty("req_id")
    private String reqId;


    @JsonProperty("http_code")
    private int httpCode;

    @JsonProperty("status_code")
    private int statusCode;

    @JsonProperty("message")
    private String msg;

    @JsonProperty("data")
    public Map<String, Object> data = null;


    public ResponseData() {}


    public ResponseData(String reqId, int httpCode, int statusCode, String text)
    {
        this.reqId = reqId;
        this.httpCode = httpCode;
        this.statusCode = statusCode;
        this.msg = text;
    }

    public ResponseData(String reqId, int httpCode, int statusCode, Map<String, Object> data)
    {
        this.reqId = reqId;
        this.httpCode = httpCode;
        this.statusCode = statusCode;
        this.data = data;
    }

    public ResponseData(int httpCode, int statusCode, String text) {
        this.httpCode = httpCode;
        this.statusCode = statusCode;
        this.msg = text;
    }

    public ResponseData(int httpCode, int statusCode, Map<String, Object> data) {
        this.httpCode = httpCode;
        this.statusCode = statusCode;
        this.data = data;
    }




    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
    public String toString(boolean withoutData) {
        if (withoutData) {
            return "ResponseData{" +
                    "req_id='" + reqId + '\'' +

                    ", http_code=" + httpCode +
                    ", status_code=" + statusCode +
                    '}';
        } else {
            return toString();
        }
    }
}
