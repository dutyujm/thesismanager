package cn.dutyujm.controller;

import cn.dutyujm.feign.InformationFeign;
import cn.dutyujm.pojo.Student;
import cn.dutyujm.pojo.Teacher;
import cn.dutyujm.pojo.Thesis;
import cn.dutyujm.pojo.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientHandler {
    @Autowired
    private InformationFeign informationFeign;


    @RequestMapping(value = "/student/studentInfo", method = RequestMethod.GET)
    public Student getStudent(@RequestParam("sid") Integer sid){
        return informationFeign.getStudent(sid);

    }

    @RequestMapping(value = "/student/chooseThesis", method = RequestMethod.POST)
    public Integer chooseThesis(@RequestParam("sid") Integer sid, @RequestParam("id") Integer id){
        return informationFeign.chooseThesis(sid,id);
    }

    @RequestMapping(value = "/student/cancelChoose", method = RequestMethod.POST)
    public Integer cancelChoose(@RequestParam("sid") Integer sid, @RequestParam("id") Integer id){
        return informationFeign.cancelChoose(sid, id);
    }

    @RequestMapping(value = "/student/insertStudent", method = RequestMethod.POST)
    public Integer insertStudent(@RequestBody Student student){
        return informationFeign.insertStudent(student);
    }

    @RequestMapping(value = "/student/transfer", method = RequestMethod.POST)
    public String transfer(@RequestParam("fromSid") Integer fromSid, @RequestParam("toSid") Integer toSid){
        return informationFeign.transfer(fromSid, toSid);
    }

    @RequestMapping(value = "/thesis/list", method = RequestMethod.GET)
    public List<Thesis> getThesisList() {
//        HttpServletResponse response
//        // 解决跨域请求问题
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Cache-Control","no-cache");
        return informationFeign.getThesisList();
    }

    @RequestMapping(value = "/thesis/findThesis",method = RequestMethod.GET )
    public Thesis  findThesisBySid(@RequestParam("sid")Integer sid){
        return informationFeign.findThesisBySid(sid);
    }

    @RequestMapping(value = "/thesis/findThesisByname",method = RequestMethod.POST)
    public List<Thesis> findThesisByname(@RequestParam("title") String title){
        return informationFeign.findThesisByname(title);
    }

    @RequestMapping(value = "/thesis/page",method = RequestMethod.GET)
    public List<Thesis> pageThesis(@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "10") Integer limit){
        return informationFeign.pageThesis(page,limit);
    }

    @RequestMapping(value = "/teacher/findByTid",method = RequestMethod.GET)
    public List<Teacher> getTeachers(@RequestParam("title_id") Integer title_id){
        return informationFeign.getTeachers(title_id);
    }

    @RequestMapping(value = "/title/getTitleAndTeachers",method = RequestMethod.GET)
    public List<Title> getTitleAndTeachers(){
        return informationFeign.getTitleAndTeachers();
    }


}
