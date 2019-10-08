package cn.dutyujm.mapper;

import cn.dutyujm.pojo.Request;

public interface RequestMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(Request record);

    int insertSelective(Request record);

    Request selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(Request record);

    int updateByPrimaryKey(Request record);
}