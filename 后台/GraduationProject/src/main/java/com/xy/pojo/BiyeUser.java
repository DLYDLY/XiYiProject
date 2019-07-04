package com.xy.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BiyeUser {

    private String userId;
    private String username;
    private String userportrait;
    private String password;
    private String phone;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date created;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updated;
    private String graceId;
    private String opendId;
    private long state;


    private BiyeGrace biyeGrace;


    public BiyeGrace getBiyeGrace() {
        return biyeGrace;
    }

    public void setBiyeGrace(BiyeGrace biyeGrace) {
        this.biyeGrace = biyeGrace;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserportrait() {
        return userportrait;
    }

    public void setUserportrait(String userportrait) {
        this.userportrait = userportrait;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getGraceId() {
        return graceId;
    }

    public void setGraceId(String graceId) {
        this.graceId = graceId;
    }

    public String getOpendId() {
        return opendId;
    }

    public void setOpendId(String opendId) {
        this.opendId = opendId;
    }

    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }


}
