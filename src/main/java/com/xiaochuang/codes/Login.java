package com.xiaochuang.codes;


import com.xiaochuang.services.userService;
import com.xiaochuang.services.userdetailService;
import com.xiaochuang.setClasses.User;
import com.xiaochuang.setClasses.UserDetail;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class Login {

    @Autowired
    @Qualifier("userService")
    private userService userservice;
    public void setUserservice(userService userservice){
        this.userservice=userservice;
    }

    @Autowired
    @Qualifier("userdetailService")
    private userdetailService userdetailservice;
    public void setUserdetailservice(userdetailService userdetailservice){this.userdetailservice=userdetailservice;}

    @RequestMapping("userLogin")
    @ResponseBody
    public Map<String,Object> userLogin (@RequestParam("username") String username,@RequestParam("password") String password){
        //参数User user是获取前端传来的数据，取对应变量构造一个user(至于对应完全与否，不影响)

        Map<String,Object> map = new HashMap<String,Object>();

        User user = userservice.loginselectuser(username,password);

        System.out.println("登录的用户 id:"+user.getUserid()+" 用户名: "+user.getUsername());


        Map<String,Integer> usertarget = getUserTarget(user.getUserid());

        map.put("loggedUser",user);
        map.put("userTarget",usertarget);


        return map;
    }



    /*
     * 读取用户个性标签
     * @Param int userid
     *
     */
    public Map<String,Integer> getUserTarget(int userid){


        /*
         *作用：
         *依次取出用户个性化的三条数据（设三条是为了更加客观吧）
         *并将相同的词频加起来
         *取出cnt的value，因为cnt的value作为真正用到的json(这里用了两层，是为了省略中间变量)
         *
         *
         * 根据   userid  来读取
         */
        UserDetail person = userdetailservice.selectPersonTargetByUserid(userid);
        JSONObject personaltarget_third = JSONObject.fromObject(JSONObject.fromObject(person.getPersonaltarget_third()).getString("cnt"));
        JSONObject personaltarget_second = JSONObject.fromObject(JSONObject.fromObject(person.getPersonaltarget_second()).getString("cnt"));
        JSONObject personaltarget_latest = JSONObject.fromObject(JSONObject.fromObject(person.getPersonaltarget_latest()).getString("cnt"));
        Iterator<String> personaltarget_third_keys = personaltarget_third.keys();
        Iterator<String> personaltarget_second_keys = personaltarget_second.keys();
        Iterator<String> personaltarget_latest_keys = personaltarget_latest.keys();


        /*
         *
         * 爷要死了，关于迭代器Iterator不能随便remove，也不要用map，尽量用List
         *
         * 这里近50行是为了把用户3层缓冲的target拿出来，把相同词的词频加起来。
         *
         * */

        String key;
        String key2;
        Integer value;
        Integer value2;

        Map<String,Integer> personaltargetAll = new HashMap<String,Integer>();
        List<String> listkeys = new ArrayList<>();
        List<Integer> listvalues = new ArrayList<>();
        int i=0;
        while(personaltarget_third_keys.hasNext()){
            key=personaltarget_third_keys.next();
            value=Integer.parseInt(personaltarget_latest.getString(key));
            listkeys.add(key);
            listvalues.add(value);
        }

        while(personaltarget_second_keys.hasNext()){
            key=personaltarget_second_keys.next();
            value=Integer.parseInt(personaltarget_second.getString(key));
            for(i=0;i<listkeys.size();i++){
                value2=listvalues.get(i);
                if(key.equals(listkeys.get(i))){
                    listvalues.set(i,value+value2);
                }
            }
        }

        while(personaltarget_latest_keys.hasNext()){
            key=personaltarget_latest_keys.next();
            value=Integer.parseInt(personaltarget_latest.getString(key));
            for(i=0;i<listkeys.size();i++){
                value2=listvalues.get(i);
                if(key.equals(listkeys.get(i))){
                    listvalues.set(i,value+value2);
                }
            }
        }
        for(i=0;i<listkeys.size();i++){
            personaltargetAll.put(listkeys.get(i),listvalues.get(i));

        }

        return personaltargetAll;
    }



}
