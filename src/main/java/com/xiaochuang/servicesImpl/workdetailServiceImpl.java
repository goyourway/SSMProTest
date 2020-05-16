package com.xiaochuang.servicesImpl;

import com.xiaochuang.mappers.workdetailMapper.workdetailMapper;
import com.xiaochuang.services.workdetailService;
import com.xiaochuang.setClasses.WorkDetail;

import java.util.List;

public class workdetailServiceImpl implements workdetailService {

    private workdetailMapper workdetailmapper;
    public void setWorkdetailmapper(workdetailMapper workdetailmapper){
      this.workdetailmapper = workdetailmapper;
  }

    @Override
    public WorkDetail selectWorkTargetByWorkid(int workid) {
        return workdetailmapper.selectWorkTargetByWorkid(workid);
    }

    @Override
    public int insertWorkTarget(int workid, String worktarget, String targettext,int emotionresult) {
        return workdetailmapper.insertWorkTarget(workid,worktarget,targettext,emotionresult);
    }

    @Override
    public int updateWorkTarget(int workid, String worktarget, String targettext) {
        return workdetailmapper.updateWorkTarget(workid,worktarget,targettext);
    }

    @Override
    public List<Integer> selectFiveWorkidByTargetText(List<String> targettext) {
        return workdetailmapper.selectFiveWorkidByTargetText(targettext);
    }

}
