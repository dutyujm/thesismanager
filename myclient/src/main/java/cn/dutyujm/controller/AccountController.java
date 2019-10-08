package cn.dutyujm.controller;

import cn.dutyujm.feign.AccountFeign;
import cn.dutyujm.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountFeign accountFeign;

    @RequestMapping(value = "/admin/login",method = RequestMethod.POST)
    public Admin login(@RequestParam("name") String name, @RequestParam("password")String password){
        return accountFeign.login(name,password);
    }
}
