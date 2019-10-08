package cn.dutyujm.controller;

import cn.dutyujm.service.TeacherService;
import cn.dutyujm.pojo.Teacher;
import cn.dutyujm.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Raymond
 * @date: 2019/9/7
 * @Description:
 **/
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/findByTid",method = RequestMethod.GET)
    public List<Teacher> getTeachers(@RequestParam("title_id") Integer title_id){
        return teacherService.getTeachers(title_id);
    }
}
