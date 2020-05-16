package com.xiaochuang.mappers.workdetailMapper;

import com.xiaochuang.setClasses.WorkDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface workdetailMapper {

    public WorkDetail selectWorkTargetByWorkid(@Param("workid") int workid);
    public int insertWorkTarget(@Param("workid") int workid,@Param("worktarget") String worktarget,@Param("targettext") String targettext,@Param("emotionresult") int emotionresult);
    public int updateWorkTarget(@Param("workid") int workid,@Param("worktarget") String worktarget,@Param("targettext") String targettext);
    public List<Integer> selectFiveWorkidByTargetText(@Param("targettext") List<String> targettext);

}
