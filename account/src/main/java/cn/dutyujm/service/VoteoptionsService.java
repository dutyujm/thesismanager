package cn.dutyujm.service;

import cn.dutyujm.pojo.Voteoptions;

import java.util.List;

public interface VoteoptionsService {
    int deleteByPrimaryKey(Integer id);

    int insert(Voteoptions record);

    int insertSelective(Voteoptions record);

    Voteoptions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Voteoptions record);

    int updateByPrimaryKey(Voteoptions record);

    List<Voteoptions> getOptionList (Integer vid);
}
