package cn.dutyujm.service;

import cn.dutyujm.pojo.Admin;

/**
 * @author: Raymond
 * @date: 2019/9/7
 * @Description:
 **/
public interface AdminService {
    int deleteByPrimaryKey(Integer aid);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer aid);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}
