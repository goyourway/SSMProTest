package com.xiaochuang.mappers.userdetailMapper;


import com.xiaochuang.setClasses.UserDetail;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;

public interface userdetailMapper {

    public UserDetail selectPersonTargetByUserid(@Param("userid") int userid);
    public int updateUserDetail(@Param("userid") int userid, @Param("personaltarget")String personaltarget);

}
