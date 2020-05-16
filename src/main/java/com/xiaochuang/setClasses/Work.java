package com.xiaochuang.setClasses;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Work {

    private int workid;
    private String type;
    private String title;
    private Date publicationtime;
    private String popular;
    private String source;
    private List<Comment> comments;
    private Map<String,Integer> worktarget;

    public int getWorkid() {
        return workid;
    }

    public void setWorkid(int workid) {
        this.workid = workid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublicationtime() {
        return publicationtime;
    }

    public void setPublicationtime(Date publicationtime) {
        this.publicationtime = publicationtime;
    }

    public String getPopular() {
        return popular;
    }

    public void setPopular(String popular) {
        this.popular = popular;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Map<String, Integer> getWorktarget() {
        return worktarget;
    }

    public void setWorktarget(Map<String, Integer> worktarget) {
        this.worktarget = worktarget;
    }
}
