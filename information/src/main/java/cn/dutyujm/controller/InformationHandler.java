package cn.dutyujm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/information")
public class InformationHandler {
    @Value("${server.port}")
    private  String port;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        return "information的端口："+this.port;
    }
}
