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
public class Test {

    @Autowired
    @Qualifier("userService")
    private userService userservice;
    public void setUserService(userService userservice){
        this.userservice=userservice;
    }


    @RequestMapping("Test")
    @ResponseBody
    public Map<Object,String> test(){

        Map map = new HashMap<Object,String>();

        map.put("errMsg","This is a suessful result!");

        return map;
    }

    @RequestMapping("getUsers")
    @ResponseBody
    public Map<String,Object> getUsers(){

        Map map = new HashMap<String,Object>();

        User user = userservice.selectAll();

        map.put("users",user);

        return map;
    }


}
