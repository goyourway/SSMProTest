package com.xiaochuang.services;

import com.google.gson.JsonObject;
import com.xiaochuang.setClasses.UserDetail;
import net.sf.json.JSONObject;

public interface userdetailService {
    public UserDetail selectPersonTargetByUserid(int userid);
    public int updateUserDetail(int userid,String personaltarget);

}
