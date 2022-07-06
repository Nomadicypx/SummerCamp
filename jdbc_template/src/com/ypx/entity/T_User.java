package com.ypx.entity;

import org.springframework.stereotype.Component;

@Component
public class T_User {
    public String getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getUserstatus() {
        return userstatus;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserstatus(String userstatus) {
        this.userstatus = userstatus;
    }

    private String userid;
    private String username;
    private String userstatus;
}
