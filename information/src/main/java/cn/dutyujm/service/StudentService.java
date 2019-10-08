package cn.dutyujm.service;

import cn.dutyujm.pojo.Student;

/**
 * @author: Raymond
 * @date: 2019/9/7
 * @Description:
 **/
public interface StudentService {
    int deleteByPrimaryKey(Integer sid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    String transfer(Integer fromSid, Integer toSid);


}
