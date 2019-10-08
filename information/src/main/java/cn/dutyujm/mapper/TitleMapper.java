package cn.dutyujm.mapper;

import cn.dutyujm.pojo.Title;

import java.util.List;

public interface TitleMapper {
    int deleteByPrimaryKey(Integer titleId);

    int insert(Title record);

    int insertSelective(Title record);

    Title selectByPrimaryKey(Integer titleId);

    int updateByPrimaryKeySelective(Title record);

    int updateByPrimaryKey(Title record);

    List<Title> getTitleAndTeachers();

//    List<Teacher> getTeachers();
}