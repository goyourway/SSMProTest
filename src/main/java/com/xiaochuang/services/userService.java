package com.xiaochuang.services;


import com.xiaochuang.setClasses.User;

public interface userService {
    public User selectAll();
    public User selectUserById(User user);
    public void insertUser(User user);

    public User loginselectuser(String username,String password);
    public int registeruser(String username,String password);
}
