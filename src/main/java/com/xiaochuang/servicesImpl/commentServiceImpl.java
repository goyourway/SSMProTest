package com.xiaochuang.servicesImpl;

import com.xiaochuang.mappers.commentMapper.commentMapper;
import com.xiaochuang.services.commentService;
import com.xiaochuang.setClasses.Comment;

import java.util.Date;
import java.util.List;

public class commentServiceImpl implements commentService {

    private commentMapper commentmapper;
    public void setCommentmapper(commentMapper commentmapper){
        this.commentmapper=commentmapper;
    }


    @Override
    public List<Comment> selectCommentsByWorkid(int workid) {
        return commentmapper.selectCommentsByWorkid(workid);
    }

    @Override
    public int insertComment(int workid, int userid, String content, Date commenttime) {
        return commentmapper.insertComment(workid,userid,content,commenttime);
    }
}
