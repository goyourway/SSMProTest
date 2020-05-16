package com.xiaochuang.servicesImpl;

import com.xiaochuang.mappers.userMapper.userMapper;
import com.xiaochuang.services.userService;
import com.xiaochuang.setClasses.User;
import org.apache.ibatis.annotations.Param;

public class userServiceImpl implements userService {

    private userMapper usermapper;

    public void setUsermapper(userMapper usermapper) {
        this.usermapper=usermapper;
    }
    @Override
    public User selectAll() {
        return usermapper.selectAll();
    }

    @Override
    public User selectUserById(User user) {
        return null;
    }

    @Override
    public void insertUser(User user) {

    }

    @Override
    public User loginselectuser(String username, String password) {
        return usermapper.loginselectuser(username,password);
    }

    @Override
    public int registeruser(String username, String password) {
        return usermapper.registeruser(username,password);
    }


}
