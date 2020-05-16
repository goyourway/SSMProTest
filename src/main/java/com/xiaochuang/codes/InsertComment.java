package com.xiaochuang.codes;


import com.xiaochuang.services.commentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class InsertComment {

    @Autowired
    @Qualifier("commentService")
    private commentService commentservice;
    public void setCommentservice(commentService commentservice) {
        this.commentservice = commentservice;
    }

    @RequestMapping("insertComment")
    @ResponseBody
    public Map<String,Object> insertComment(@RequestParam("workid") int workid, @RequestParam("userid") int userid,@RequestParam("content") String content){

        Map<String,Object> map = new HashMap<String,Object>();

        Date commenttime = new Date();

        int res = commentservice.insertComment(workid, userid, content, commenttime);
        if(res == 1){
            map.put("message","评论成功");
            System.out.println("评论成功");
        }else{
            map.put("message","评论失败");
            System.out.println("评论失败");
        }



        return map;
    }

}
