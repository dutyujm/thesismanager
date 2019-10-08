package cn.dutyujm.service.impl;

import cn.dutyujm.mapper.StudentMapper;
import cn.dutyujm.pojo.Student;
import cn.dutyujm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Raymond
 * @date: 2019/9/7
 * @Description:
 **/
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int deleteByPrimaryKey(Integer sid) {
        return 0;
    }

    @Override
    public int insert(Student record) {

         studentMapper.insert(record);
                return record.getSid();
    }

    @Override
    public int insertSelective(Student record) {
        return 0;
    }

    @Override
    public Student selectByPrimaryKey(Integer sid) {

        return studentMapper.selectByPrimaryKey(sid);
    }

    @Override
    public int updateByPrimaryKeySelective(Student record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Student record) {

        return studentMapper.updateByPrimaryKey(record);
    }

//    @Override
//    public Thesis findThesisBySid(Integer sid){
//
//        return studentMapper.findThesisBySid(sid);
//    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public String transfer(Integer fromSid, Integer toSid) {
        //实现学生互换论文

        Student fromStudent = studentMapper.selectByPrimaryKey(fromSid);
        Student toStudent = studentMapper.selectByPrimaryKey(toSid);

        int tmp = fromStudent.getThesisId();
        fromStudent.setThesisId(toStudent.getThesisId());
        toStudent.setThesisId(tmp);
        studentMapper.updateByPrimaryKey(fromStudent);

            int i = 1/0;

        studentMapper.updateByPrimaryKey(toStudent);

        return "Nice";
    }
}
