package cn.dutyujm.controller;

import cn.dutyujm.pojo.Student;
import cn.dutyujm.pojo.Thesis;
import cn.dutyujm.service.StudentService;
import cn.dutyujm.service.ThesisService;
import cn.dutyujm.websocket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.Session;
import java.io.IOException;
import java.util.List;

/**
 * @author: Raymond
 * @date: 2019/9/7
 * @Description:
 **/
@RestController
@RequestMapping("/student")
public class StudentController {

    private Thesis thesis;
    private Student student;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ThesisService thesisService;

    @RequestMapping(value = "/studentInfo", method = RequestMethod.GET)
    public Student getStudent(@RequestParam ("sid") Integer sid){
        return studentService.selectByPrimaryKey(sid);
    }

    @RequestMapping(value = "/chooseThesis", method = RequestMethod.POST)
    public Integer chooseThesis(@RequestParam("sid") Integer sid, @RequestParam("id") Integer id){

        thesis = thesisService.selectByPrimaryKey(id);
        student = studentService.selectByPrimaryKey(sid);

        if (thesis.getSelected() == true && student.getThesisId()==null) {
            thesis.setSelected(false);
            student.setThesisId(id);

            /**
             * 从这儿起秀儿要开始操作了
             */
            String message = "学生："+student.getName()+" 选择了您的课题："+thesis.getTitle(); //将要发送给教师的信息写好

            WebSocket webSocket = new WebSocket();                //新建一个webSocket类，目的很简单，调用websocket类中的静态变量electricSocketMap

            for(String key : webSocket.electricSocketMap.keySet()) {                //遍历所有已有的session对话
                if (key.equals(thesis.getTid().toString())){                        //找到所需要发送信息的那个教师的sessions对话链
                    try {
                        List<Session> sessionList = webSocket.electricSocketMap.get(key);  //获取对话链

                        for(Session session1 : sessionList){
                            session1.getBasicRemote().sendText(message);      //向所选教师的每个会话都发送信息（该用户要是同时在两个地方登录）
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } else {
            return 0;
        }
        thesisService.updateByPrimaryKey(thesis);
        studentService.updateByPrimaryKey(student);
        return 1;


    }

    @RequestMapping(value="/cancelChoose",method = RequestMethod.POST)
    public Integer cancelChoose(@RequestParam("sid")Integer sid,@RequestParam("id")Integer id){
        thesis = thesisService.selectByPrimaryKey(id);
        student = studentService.selectByPrimaryKey(sid);
        if (thesis.getSelected() == false && student.getThesisId()!=null) {
            thesis.setSelected(true);
            student.setThesisId(null);
        } else {
            return 0;
        }
        thesisService.updateByPrimaryKey(thesis);
        studentService.updateByPrimaryKey(student);
        return 1;
    }

    @RequestMapping(value = "/insertStudent",method = RequestMethod.POST)
    public Integer insertStudent(@RequestBody Student student){

       return  studentService.insert(student);

    }

    @RequestMapping(value = "/transfer",method = RequestMethod.POST)
    public String transfer(@RequestParam("fromSid")Integer fromSid,@RequestParam("toSid") Integer toSid){

      return studentService.transfer(fromSid,toSid);

    }





}
