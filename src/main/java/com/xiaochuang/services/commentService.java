package com.xiaochuang.services;

import com.xiaochuang.setClasses.Comment;

import java.util.Date;
import java.util.List;

public interface commentService {


    public List<Comment> selectCommentsByWorkid(int workid);
    public int insertComment(int workid,int userid,String content,Date commenttime);
}
