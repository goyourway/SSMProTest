package com.xiaochuang.setClasses;


import net.sf.json.JSONObject;


public class UserDetail {
    private int userid;
    private String personaltarget_third;
    private String personaltarget_second;
    private String personaltarget_latest;
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getPersonaltarget_third() {
        return personaltarget_third;
    }

    public void setPersonaltarget_third(String personaltarget_third) {
        this.personaltarget_third = personaltarget_third;
    }

    public String getPersonaltarget_second() {
        return personaltarget_second;
    }

    public void setPersonaltarget_second(String personaltarget_second) {
        this.personaltarget_second = personaltarget_second;
    }

    public String getPersonaltarget_latest() {
        return personaltarget_latest;
    }

    public void setPersonaltarget_latest(String personaltarget_latest) {
        this.personaltarget_latest = personaltarget_latest;
    }
}
