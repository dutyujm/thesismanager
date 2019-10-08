package cn.dutyujm.feign;

import cn.dutyujm.pojo.Student;
import cn.dutyujm.pojo.Teacher;
import cn.dutyujm.pojo.Thesis;
import cn.dutyujm.pojo.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import java.io.IOException;
import java.util.List;

@FeignClient(value = "information")

public interface InformationFeign {

    @RequestMapping(value = "/student/studentInfo", method = RequestMethod.GET)
     Student getStudent(@RequestParam("sid") Integer sid);

    @RequestMapping(value = "/student/chooseThesis", method = RequestMethod.POST)
     Integer chooseThesis(@RequestParam("sid") Integer sid, @RequestParam("id") Integer id);

    @RequestMapping(value = "/student/cancelChoose", method = RequestMethod.POST)
     Integer cancelChoose(@RequestParam("sid") Integer sid, @RequestParam("id") Integer id);

    @RequestMapping(value = "/student/insertStudent", method = RequestMethod.POST)
     Integer insertStudent(@RequestBody Student student);

    @RequestMapping(value = "/student/transfer", method = RequestMethod.POST)
     String transfer(@RequestParam("fromSid") Integer fromSid, @RequestParam("toSid") Integer toSid);

    @RequestMapping(value = "/thesis/list", method = RequestMethod.GET)
     List<Thesis> getThesisList() ;

    @RequestMapping(value = "/thesis/findThesis",method = RequestMethod.GET )
     Thesis  findThesisBySid(@RequestParam("sid")Integer sid);

    @RequestMapping(value = "/thesis/findThesisByname",method = RequestMethod.POST)
     List<Thesis> findThesisByname(@RequestParam("title") String title);

    @RequestMapping(value = "/thesis/thesispage",method = RequestMethod.GET)
     List<Thesis> pageThesis(@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "10") Integer limit);

    @RequestMapping(value = "/teacher/findByTid",method = RequestMethod.GET)
     List<Teacher> getTeachers(@RequestParam("title_id") Integer title_id);

    @RequestMapping(value = "/title/getTitleAndTeachers",method = RequestMethod.GET)
     List<Title> getTitleAndTeachers();


}
