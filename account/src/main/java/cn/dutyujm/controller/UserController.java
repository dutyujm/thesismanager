package cn.dutyujm.controller;

import cn.dutyujm.pojo.Admin;
import cn.dutyujm.pojo.People;
import cn.dutyujm.pojo.User;
import cn.dutyujm.pojo.UserMatch;
import cn.dutyujm.service.AdminService;
import cn.dutyujm.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.jcraft.jsch.SftpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
@CrossOrigin
@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/student",method = RequestMethod.GET)
    public User student(@RequestParam("sid") Integer sid){
        return userService.selectBySid2(sid);
    }
    @RequestMapping(value = "/getstudent",method = RequestMethod.GET)
    public User getstudent(@RequestParam("sid") Integer sid){
        return userService.selectBySid(sid);
    }

    @RequestMapping(value = "/updatestudent",method = RequestMethod.POST)
    public int updatestudent(@RequestBody User user){
        return userService.updateBySid(user);
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("sid") String sid, @RequestParam("passWord")String passWord){
        if( 1==userService.login(sid,passWord)){
            return "success";
        }
        return "false";
    }


    //模糊查询
    @RequestMapping(value = "/findUserByname",method = RequestMethod.POST)
    public List<User> findUserByname(@RequestParam("title") String title,@RequestParam(defaultValue = "1") Integer page,
                                     @RequestParam(defaultValue = "10") Integer limit ){
        return userService.selectUserByName2(title,page,limit);
    }



    @RequestMapping(value = "/countSelectUserByName",method = RequestMethod.GET)
    public Integer countSelectUserByName(@RequestParam("title") String title){
        return userService.countSelectUserByName(title);


    }


    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    public Integer insertStudent(@RequestBody User user){
        return  userService.insert(user);

    }

    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public Integer deleteUser(@RequestParam("sid") Integer sid){
        return userService.deleteBySid(sid);
    }

    @RequestMapping(value = "/add/photo",method = RequestMethod.POST)
    public String upload(MultipartFile file){
        String s = userService.updatePhoto(file);
        return "R.ok();";
    }

    @RequestMapping(value = "/update/photo",method = RequestMethod.POST)
    public String sftpupdatePhoto(MultipartFile file,@RequestParam("sid") Integer sid) throws Exception {
        String photo = userService.sftpupdatePhoto(file,sid);

        User user = userService.selectBySid(sid);

        user.setPhoto(photo);

        userService.updateByPrimaryKey(user);

        SocketController.upToBaidu(photo,sid);
        return user.getPhoto();

    }

    @RequestMapping(value = "/search/photo",method = RequestMethod.POST)
    public List<UserMatch> searchPhoto(MultipartFile file) throws Exception {
        String photo = userService.search(file);

        List<People> peopleList=  SocketController.dome(photo);
        for(int i = 0; i < peopleList.size() ; i++)
        {
            People p = peopleList.get(i);
            System.out.println(p.getUser_id()+p.getScore());
        }

        List<UserMatch> userMatchList = new ArrayList<UserMatch>();
        for(int i = 0; i < peopleList.size() ; i++)
        {
            People p = peopleList.get(i);
            String s= p.getUser_id();
            User user = userService.selectBySid(Integer.parseInt(s));
            System.out.println(user.getName());
            UserMatch userMatch= new UserMatch();
            userMatch.setEmail(user.getEmail());
            userMatch.setName(user.getName());
            userMatch.setPhoneNumber(user.getPhoneNumber());
            userMatch.setPhoto(user.getPhoto());
            userMatch.setQq(user.getQq());
            userMatch.setScore(p.getScore());
            userMatch.setSid(user.getSid());
//
              userMatchList.add(userMatch);
        }
     return  userMatchList;
    }






}
