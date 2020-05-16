package com.xiaochuang.codes;


import com.xiaochuang.services.userdetailService;
import com.xiaochuang.setClasses.UserDetail;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.*;

@Controller
public class DockerPost {

    @Autowired
    @Qualifier("userdetailService")
    private userdetailService userdetailservice;



    @RequestMapping("api/predict")
    @ResponseBody
    public Map<String,Object> predict(@RequestParam("text") String text) throws ClientProtocolException, IOException {

        Map<String,Object> map = new HashMap<String,Object>();

        //确定好的post url
        String url = "http://101.200.182.169:5000/api/predict/";

        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建post对象
        HttpPost post = new HttpPost(url);
        //创建list数据对象，用于存放提交的data
        List<NameValuePair> formDataList = new ArrayList<>();
        //增加提交的数据
        formDataList.add(new BasicNameValuePair("text",text));
        //将调教的数据转换编码存在entity对象中
        StringEntity formData = new UrlEncodedFormEntity(formDataList,"UTF-8");
        post.setEntity(formData);
        //发送post请求
        CloseableHttpResponse response = httpClient.execute(post);
        //获取响应数据
        HttpEntity entity = response.getEntity();
        //把响应数据转换为字符串
        String predict = EntityUtils.toString(entity);

        JSONObject emotion = JSONObject.fromObject(predict);

        int emotionresult = Integer.parseInt(emotion.getString("result"));

        System.out.println("emotionresult  "+emotionresult);

        //关闭
        response.close();
        httpClient.close();



        map.put("emotionresult",emotionresult);


        return map;
    }


    @RequestMapping("api/wordcount")
    @ResponseBody
    public Map<String,Object> wordCount(@RequestParam("top") int top,@RequestParam("texts") String... texts) throws ClientProtocolException, IOException {

        //根据借口，top表示一次选多少个最高的词频
        String topstr = Integer.toString(top);

        Map<String,Object> map = new HashMap<String,Object>();

        //确定好的post url
        String url = "http://101.200.182.169:5000/api/wordcount/";

        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建post对象
        HttpPost post = new HttpPost(url);
        //创建list数据对象，用于存放提交的data
        List<NameValuePair> formDataList = new ArrayList<>();
        //增加提交的数据
        formDataList.add(new BasicNameValuePair("top",topstr));

        //循环把需要统计词频的文本放进去
        for(int i=0;i<texts.length;i++){
            //System.out.println(texts[i]);
            formDataList.add(new BasicNameValuePair("texts",texts[i]));
        }


        //将调教的数据转换编码存在entity对象中
        StringEntity formData = new UrlEncodedFormEntity(formDataList,"UTF-8");

        post.setEntity(formData);
        //发送post请求
        CloseableHttpResponse response = httpClient.execute(post);
        //获取响应数据
        HttpEntity entity = response.getEntity();
        //把响应数据转换为字符串
        String wordcount = EntityUtils.toString(entity);
        JSONObject jsonwordcount = JSONObject.fromObject(wordcount);



        //关闭
        response.close();
        httpClient.close();





        /*排个序
        List<Integer> shuzua=new ArrayList<>();
        List<String> shuzub=new ArrayList<>();
        shuzua.add(15);
        shuzua.add(99);
        shuzua.add(108);
        shuzub.add("  xulie1  ");
        shuzub.add("  xulie2  ");
        shuzub.add("  xulie3  ");
        for(int a=0;a<shuzua.size();a++){
            for(int b=0;b<shuzua.size()-1;b++){
                if(shuzua.get(b)<shuzua.get(b+1)){
                    int temp=shuzua.get(b);
                    shuzua.set(b,shuzua.get(b+1));
                    shuzua.set(b+1,temp);

                    String temp2=shuzub.get(b);
                    shuzub.set(b,shuzub.get(b+1));
                    shuzub.set(b+1,temp2);
                }
            }
        }

        for(int a=0;a<shuzua.size();a++){
            System.out.println("排序测试"+shuzub.get(a)+shuzua.get(a));
        }
*/


        //这里多此一举用jsonwordcount.toString()是为了避免unicode的出现g
        //int res = userdetailservice.updateUserDetail(1,jsonwordcount.toString());

 //       System.out.println("selected: "+userdetailservice.selectPersonTargetByUserid(1));

        map.put("wordcount",jsonwordcount);




        return map;
    }

    /*
     * unicode编码转中文——————————————————然而并没有用到
     */
    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

}
