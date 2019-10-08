package cn.dutyujm.service;

import cn.dutyujm.pojo.Teacher;

import java.util.List;

/**
 * @author: Raymond
 * @date: 2019/9/7
 * @Description:
 **/
public interface TeacherService {
    int deleteByPrimaryKey(Integer tid);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    List<Teacher> getTeachers(Integer title_id);


}
