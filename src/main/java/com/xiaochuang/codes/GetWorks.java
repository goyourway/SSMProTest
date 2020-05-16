package com.xiaochuang.codes;


import com.xiaochuang.services.*;
import com.xiaochuang.setClasses.Comment;
import com.xiaochuang.setClasses.Work;
import com.xiaochuang.setClasses.WorkDetail;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class GetWorks {

    private int startindex=0;

    @Autowired
    @Qualifier("workService")
    private workService workservice;
    public void setWorkservice(workService workservice) {
        this.workservice = workservice;
    }

    @Autowired
    @Qualifier("commentService")
    private commentService commentservice;
    public void setCommentservice(commentService commentservice) {
        this.commentservice = commentservice;
    }


    @Autowired
    @Qualifier("workdetailService")
    private workdetailService workdetailservice;

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


    //---------------获取所有作品（每次获取十个）
    @RequestMapping("selectFiveWorksByDate")
    @ResponseBody
    public Map<String,Object> selectFiveWorksByDate(){

        Map<String,Object> map = new HashMap<String,Object>();

        List<Work> FiveWorksByDate = workservice.selectTenWorksByDate(startindex);

        //和前端对接后打算用session或cookie存放startindex
        //startindex += 10;


        for(int i=0;i<FiveWorksByDate.size();i++){
            List<Comment> comments=commentservice.selectCommentsByWorkid(FiveWorksByDate.get(i).getWorkid());
            FiveWorksByDate.get(i).setComments(comments);

            //自定义函数，返回的是worktarget的map
            Map<String,Integer> workTarget = selectWorkTargetByWorkid((FiveWorksByDate.get(i).getWorkid()));
            FiveWorksByDate.get(i).setWorktarget(workTarget);
        }


        map.put("FiveWorksByDate",FiveWorksByDate);

        System.out.println(FiveWorksByDate);



        return map;
    }


    //------------根据标题（关键字）获取作品
    @RequestMapping("selectWorkByTitle")
    @ResponseBody
    public Map<String,Object> selectWorkByTitle(@RequestParam("title") String title){

        Map<String,Object> map = new HashMap<String,Object>();


        List<Work> WorksByTitle = workservice.selectWorkByTitle(title);



        for(int i=0;i<WorksByTitle.size();i++){

            List<Comment> comments=commentservice.selectCommentsByWorkid(WorksByTitle.get(i).getWorkid());
            WorksByTitle.get(i).setComments(comments);

            //自定义函数，返回的是worktarget的map
            Map<String,Integer> workTarget = selectWorkTargetByWorkid((WorksByTitle.get(i).getWorkid()));
            WorksByTitle.get(i).setWorktarget(workTarget);
        }



        map.put("WorksByTitle",WorksByTitle);
        System.out.println(WorksByTitle);

        return map;
    }

    /*
    *
    * 获取作品细节
    *
     */
    public Map<String,Integer> selectWorkTargetByWorkid(int workid){

        Map<String,Integer> mapWorkTarget= new HashMap<String,Integer>();

        WorkDetail workDetail = workdetailservice.selectWorkTargetByWorkid(workid);

        JSONObject workTarget = JSONObject.fromObject(JSONObject.fromObject(workDetail.getWorktarget()).getString("cnt"));

        Iterator<String> workTarget_keys = workTarget.keys();

        List<String> listkeys = new ArrayList<>();
        List<Integer> listvalues = new ArrayList<>();

        String key;
        Integer value;

        while(workTarget_keys.hasNext()){
            key=workTarget_keys.next();
            value=Integer.parseInt(workTarget.getString(key));
            listkeys.add(key);
            listvalues.add(value);
        }

        for(int i=0;i<listkeys.size();i++){
            mapWorkTarget.put(listkeys.get(i),listvalues.get(i));

        }

        return workTarget;
    }



    @RequestMapping("selectFiveWorksByTargetTextFromUserid")
    @ResponseBody
    public Map<String,Object> selectFiveWorksByTargetTextFromUserid(@RequestParam("userid") int userid){

        Map<String,Object> map = new HashMap<String,Object>();

        Login login = new Login();
        login.setUserdetailservice(userdetailservice);
        login.setUserservice(userservice);

        Map<String,Integer> userTargets = login.getUserTarget(userid);
        List<String> userTargetText = new ArrayList<>();

        //根据users的三重usertarget的所有key来匹配workdetails的targettext
        int i=0;
        for(String key : userTargets.keySet()){
           userTargetText.add(key);
           System.out.println(userTargetText.get(i++));
        }

        System.out.println("usertarget"+userTargetText.size());

        //真正调用selectFiveWorksByTargetText函数通过标签来检索works
        map = selectFiveWorksByTargetText(userTargetText);

        return map;

    }

/*
通过标签来找出相应作品（文本对文本）
@Param List<String> targettext
 */
    public Map<String,Object> selectFiveWorksByTargetText(List<String> targettext){

       Map<String,Object> map = new HashMap<String,Object>();

        //调用workdetailservice搜索workdetail表
        List<Integer> workidlist = workdetailservice.selectFiveWorkidByTargetText(targettext);

        //调用workservice搜索work表
        List<Work> WorksByTargetText=workservice.selectFiveWorksByWorkid(workidlist);

        System.out.println("WorksByTargetText"+WorksByTargetText);

        for(int i=0;i<WorksByTargetText.size();i++){

            List<Comment> comments=commentservice.selectCommentsByWorkid(WorksByTargetText.get(i).getWorkid());
            WorksByTargetText.get(i).setComments(comments);

            //自定义函数，返回的是worktarget的map
            Map<String,Integer> workTarget = selectWorkTargetByWorkid((WorksByTargetText.get(i).getWorkid()));
            WorksByTargetText.get(i).setWorktarget(workTarget);
        }



        map.put("WorksByTargetText",WorksByTargetText);
        System.out.println(WorksByTargetText);

        return map;

    }

}
