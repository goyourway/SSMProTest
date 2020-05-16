package com.xiaochuang.codes;

import com.xiaochuang.services.userService;
import com.xiaochuang.setClasses.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class Register {


    @Autowired
    @Qualifier("userService")
    private userService userservice;
    public void setuserService(userService userservice){
        this.userservice=userservice;
    }

    @RequestMapping("userRegister")
    @ResponseBody
    public Map<String,Object> userLogin (@RequestParam("username") String username, @RequestParam("password") String password) {
        //参数User user是获取前端传来的数据，取对应变量构造一个user(至于对应完全与否，不影响)

        Map<String, Object> map = new HashMap<String, Object>();

        //res为插入的记录数 1则为插入一条
        int res = userservice.registeruser(username, password);
        if (res == 1) {
            System.out.println("注册成功");
            map.put("message", "注册成功");
        } else {
            System.out.println("注册失败");
            map.put("message", "注册失败");
        }


        return map;
    }
}
