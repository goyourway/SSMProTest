package com.xiaochuang.servicesImpl;

import com.google.gson.JsonObject;
import com.xiaochuang.mappers.userdetailMapper.userdetailMapper;
import com.xiaochuang.services.userdetailService;
import com.xiaochuang.setClasses.UserDetail;
import net.sf.json.JSONObject;

public class userdetailServiceImpl implements userdetailService {

    private userdetailMapper userdetailmapper;
    public void setUserdetailmapper(userdetailMapper userdetailmapper){
        this.userdetailmapper=userdetailmapper;
    }


    @Override
    public UserDetail selectPersonTargetByUserid(int userid) {
        return userdetailmapper.selectPersonTargetByUserid(userid);
    }

    @Override
    public int updateUserDetail(int userid, String personaltarget) {
        return userdetailmapper.updateUserDetail(userid,personaltarget);
    }
}
