package com.xiaochuang.mappers.commentMapper;

import com.xiaochuang.setClasses.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface commentMapper {

    public List<Comment> selectCommentsByWorkid(@Param("workid") int workid);
    public int insertComment(@Param("workid") int workid, @Param("userid") int userid,@Param("content") String content,@Param("commenttime") Date commenttime);
}
