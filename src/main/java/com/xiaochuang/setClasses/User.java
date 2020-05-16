package com.xiaochuang.setClasses;

public class User {
    private int userid;
    private String username;
    private String password;
    private String personaltarget;
    private String browsehistory;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonaltarget() {
        return personaltarget;
    }

    public void setPersonaltarget(String personaltarget) {
        this.personaltarget = personaltarget;
    }

    public String getBrowsehistory() {
        return browsehistory;
    }

    public void setBrowsehistory(String browsehistory) {
        this.browsehistory = browsehistory;
    }
}
