package cn.dutyujm.mapper;

import cn.dutyujm.pojo.Uservote;

public interface UservoteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Uservote record);

    int insertSelective(Uservote record);

    Uservote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Uservote record);

    int updateByPrimaryKey(Uservote record);

    int oneChoice(Integer vid, Integer sid,  Integer optionid) ;
}