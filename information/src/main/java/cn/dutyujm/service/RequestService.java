package cn.dutyujm.service;

import cn.dutyujm.pojo.Request;

/**
 * @author: Raymond
 * @date: 2019/9/7
 * @Description:
 **/
public interface RequestService {
    int deleteByPrimaryKey(Integer rid);

    int insert(Request record);

    int insertSelective(Request record);

    Request selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(Request record);

    int updateByPrimaryKey(Request record);
}
