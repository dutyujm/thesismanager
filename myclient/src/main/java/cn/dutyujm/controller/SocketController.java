package cn.dutyujm.controller;

import cn.dutyujm.feign.FeignService;
import cn.dutyujm.pojo.User;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("user")
public class SocketController {
    @Autowired
    private FeignService feignService;

    //    @RequestMapping(value = "/list", method = RequestMethod.POST)
//    public Infor getData(@RequestParam("access_token") String access_token, @RequestBody Picture picture) {
//
//        return feignService.getData(access_token, picture);
//    }
    public static void main(String[] args) throws Exception {
        SocketController.dome();

    }

    public static void dome() throws Exception {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        String entityStr = null;
        CloseableHttpResponse response = null;

        try {

            // 创建POST请求对象
            HttpPost httpPost = new HttpPost("https://aip.baidubce.com/rest/2.0/face/v3/search?access_token=24.484e08fc45b48f61841738e1584ea9ad.2592000.1571456347.282335-17278586");

            /*
             * 添加请求参数
             */
            // 创建请求参数
            List<NameValuePair> list = new LinkedList<>();
            BasicNameValuePair param1 = new BasicNameValuePair("image", "http://101.132.110.112:8080/markdownpicture/png.png");
            BasicNameValuePair param2 = new BasicNameValuePair("image_type", "URL");
            BasicNameValuePair param3 = new BasicNameValuePair("group_id_list",
                    "student");
            BasicNameValuePair param4 = new BasicNameValuePair("max_user_num",
                    "3");
            list.add(param1);
            list.add(param2);
            list.add(param3);
            list.add(param4);
            // 使用URL实体转换工具
            UrlEncodedFormEntity entityParam = new UrlEncodedFormEntity(list, "UTF-8");
            httpPost.setEntity(entityParam);

            /*
             * 添加请求头信息
             */
            httpPost.addHeader("Content-Type", "application/json");

            // 执行请求
            response = httpClient.execute(httpPost);
            // 获得响应的实体对象
            HttpEntity entity = response.getEntity();
            // 使用Apache提供的工具类进行转换成字符串

            entityStr = EntityUtils.toString(entity, "UTF-8");
//            Infor infor = (Infor)entity;
        //    System.out.println("标志"+response.getEntity());
          //  System.out.println("标志1"+JSONObject.parse(entityStr));
            JSONObject jo = (JSONObject) JSONObject.parse(entityStr);
           // System.out.println("标志2"+jo.getString("result"));
            JSONObject jo1 = (JSONObject) JSONObject.parse(jo.getString("result"));
           // System.out.println("标志1"+jo1.getString("user_list"));
            String peopleList = jo1.getString("user_list");
            List<User> persons = new ArrayList<User>();
            Gson gson = new Gson();

            List<User> ps = gson.fromJson(peopleList, new TypeToken<List<User>>(){}.getType());
            for(int i = 0; i < ps.size() ; i++)
            {
                User p = ps.get(i);
                System.out.println(p.getUser_id());
            }
//            System.out.println("标志3"+JSONObject.parse(jo.getString("result")));
//
//            JSONObject jo2 = (JSONObject) JSONObject.parse(entityStr);
//            System.out.println("标志2"+jo2.getString("result"));
            // System.out.println(Arrays.toString(response.getAllHeaders()));

        } catch (ClientProtocolException e) {
            System.err.println("Http协议出现问题");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("IO异常");
            e.printStackTrace();
        } finally {
            // 释放连接
            if (null != response) {
                try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                    System.err.println("释放连接出错");
                    e.printStackTrace();
                }
            }
        }

        // 打印响应内容
        System.out.println(entityStr);

    }


}