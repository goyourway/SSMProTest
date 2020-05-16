package com.xiaochuang.services;

import com.xiaochuang.setClasses.WorkDetail;

import java.util.List;

public interface workdetailService {

    public WorkDetail selectWorkTargetByWorkid(int workid);
    public int insertWorkTarget(int workid,String worktarget,String targettext, int emotionresult);
    public int updateWorkTarget(int workid, String worktarget,String targettext);
    public List<Integer> selectFiveWorkidByTargetText(List<String> targettext);
}
