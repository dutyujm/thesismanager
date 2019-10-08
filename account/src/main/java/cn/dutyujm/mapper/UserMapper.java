package cn.dutyujm.mapper;

import cn.dutyujm.pojo.Admin;
import cn.dutyujm.pojo.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int updateBySid(User record);
    User login(String sid, String password);

    User selectBySid(Integer id);
    User selectBySid2(Integer id);
    List<User> selectUserByName(String title,Integer tmp,Integer limit);

    int deleteBySid (Integer sid);


    int countSelectUserByName(String title);

}