package cn.dutyujm.service;

import cn.dutyujm.pojo.Thesis;

import java.util.List;

/**
 * @author: Raymond
 * @date: 2019/9/7
 * @Description:
 **/
public interface ThesisService {
    int deleteByPrimaryKey(Integer id);

    int insert(Thesis record);

    int insertSelective(Thesis record);

    Thesis selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Thesis record);

    int updateByPrimaryKey(Thesis record);

    List<Thesis> getThesisList();

//    boolean updateThesisSelected(Integer id);

    Thesis selectThesisBySid(Integer sid);

    List<Thesis> selectThesisByName(String title);

    List<Thesis> pageThesis(Integer page, Integer limit);


}
