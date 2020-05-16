package com.xiaochuang.codes;


import com.xiaochuang.services.workService;
import com.xiaochuang.services.workdetailService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

@Controller
public class SetWorkAndDetails {

    @Autowired
    @Qualifier("workdetailService")
    private workdetailService workdetailservice;

    @Autowired
    @Qualifier("workService")
    private workService workservice;


    //用来把wordcount里的map转成只有key的list，
    public List<String> mapToListString(){

        List<String> worktargettext = new ArrayList<>();

         return worktargettext;
    }




    @RequestMapping("insertWork")
    @ResponseBody
    public Map<String,Object> insertWork (@RequestParam("title") String title,@RequestParam("type") String type,@RequestParam("texts") String texts) throws IOException {

        Map<String,Object> map = new HashMap<String,Object>();

        //词频最高的五个
        int top=5;
        //调用DockerPost里的wordCount方法对docker进行api调用
        DockerPost dockerpost = new DockerPost();
        Map<String,Object> wordcount = dockerpost.wordCount(top,texts);

        //取出wordcount的实际内容
        JSONObject jsontext = (JSONObject) wordcount.get("wordcount");

        //worktarget对应workdetail数据表里的worktarget
        String worktarget = jsontext.getString("cnt");

        //targettext对应workdetail数据表里的targettext
        String targettext = "";

        JSONObject jsontext2 =  JSONObject.fromObject(worktarget);

        int emotionresult = (int) dockerpost.predict(texts).get("emotionresult");

        //用于遍历取出keys
        Iterator<String> jsontext2_keys = jsontext2.keys();
        while(jsontext2_keys.hasNext()){
            String key = jsontext2_keys.next();
            targettext=targettext.concat(key+",");
        }

        System.out.println(targettext);

        int workres = workservice.insertWork(title,type);

        int newworkid = workservice.selectWorkidByTitle(title);

        int workdetailres = workdetailservice.insertWorkTarget(newworkid,worktarget,targettext,emotionresult);

        if(workres == 1 && workdetailres ==1){
            map.put("message","新增作品成功");
            map.put("wordcount",jsontext2);
            map.put("emotionresult",emotionresult);
        }else {
            map.put("message","新增作品失败");
        }

        return map;
    }




    @RequestMapping("updateWorkDetails")
    @ResponseBody
    public int updateWorkDetails(@RequestParam("workid") int workid,@RequestParam("worktarget")String worktarget,@RequestParam("targettext") String targettext){

        return workdetailservice.updateWorkTarget(workid,worktarget,targettext);
    }




}
