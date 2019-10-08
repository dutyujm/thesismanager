package cn.dutyujm.controller;

import cn.dutyujm.pojo.Admin;
import cn.dutyujm.service.AdminService;
import cn.dutyujm.service.RedisService;
//import cn.dutyujm.utils.RedisClient;
import io.lettuce.core.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private AdminService adminService;

//
//    @Autowired
//    private RedisService redisService ;


//    @Autowired
//    private RedisClient redis;

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam ("name") String name, @RequestParam("passWord")String passWord){
        if (adminService.login(name,passWord)==1){
            return "success";
        }
        return "false";
    }


    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public boolean selectByTitle(){

        String key = "User_55115_1_10";//"session_"+sid+"_"+"Connect";
////        ValueOperations<String, List<Session>> operations = redisTemplate.opsForValue();
//        try {

        boolean hasKey = redisTemplate.hasKey(key);

        return hasKey;
//        return adminService.selectByTitle(name);
    }






}
