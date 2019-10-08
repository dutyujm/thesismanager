package cn.dutyujm.controller;

import cn.dutyujm.pojo.Title;
import cn.dutyujm.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Raymond
 * @date: 2019/9/7
 * @Description:
 **/
@RestController
@RequestMapping("/title")
public class TitleController {

    @Autowired
    private TitleService titleService;


    @RequestMapping(value = "/getTitleAndTeachers",method = RequestMethod.GET)
    public List<Title> getTitleAndTeachers(){
        return titleService.getTitleAndTeachers();
    }

}
