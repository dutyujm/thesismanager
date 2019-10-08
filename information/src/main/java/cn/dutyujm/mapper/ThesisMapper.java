package cn.dutyujm.mapper;

import cn.dutyujm.pojo.Thesis;

import java.util.List;

public interface ThesisMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Thesis record);

    int insertSelective(Thesis record);

    Thesis selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Thesis record);

    int updateByPrimaryKey(Thesis record);

    List<Thesis> getThesisList();

    Thesis selectThesisBySid(Integer sid);

    List<Thesis> selectThesisByName(String title);

    List<Thesis> pageThesis(Integer tmp, Integer limit);


}