package cn.dutyujm.mapper;

import cn.dutyujm.pojo.Admin;

public interface AdminMapper {
     Admin login(String name,String password);
     Admin selectByName(String name);
     Admin selectByTitle(String name);

}
