package cn.dutyujm.service;

import cn.dutyujm.pojo.Title;

import java.util.List;

/**
 * @author: Raymond
 * @date: 2019/9/7
 * @Description:
 **/
public interface TitleService {
    int deleteByPrimaryKey(Integer titleId);

    int insert(Title record);

    int insertSelective(Title record);

    Title selectByPrimaryKey(Integer titleId);

    int updateByPrimaryKeySelective(Title record);

    int updateByPrimaryKey(Title record);

    List<Title> getTitleAndTeachers();

}
