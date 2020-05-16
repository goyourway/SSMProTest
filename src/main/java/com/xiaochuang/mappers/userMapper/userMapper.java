package com.xiaochuang.mappers.userMapper;


import com.xiaochuang.setClasses.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface userMapper {
    public User selectAll();
    public User selectUserById(User user);
    public void insertUser(User user);

    public User loginselectuser(@Param("username") String username,@Param("password") String password);
    public int registeruser(@Param("username") String username,@Param("password") String password);
}
