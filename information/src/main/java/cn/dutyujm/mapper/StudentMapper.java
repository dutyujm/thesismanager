package cn.dutyujm.mapper;

import cn.dutyujm.pojo.Student;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

//    Thesis findThesisBySid(Integer sid);

    int transfer(Integer fromSid, Integer toSid);

}