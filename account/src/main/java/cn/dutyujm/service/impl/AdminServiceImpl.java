package cn.dutyujm.service.impl;

import cn.dutyujm.mapper.AdminMapper;
import cn.dutyujm.pojo.Admin;
import cn.dutyujm.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author: Raymond
 * @date: 2019/9/7
 * @Description:
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public int deleteByPrimaryKey(Integer aid) {
        return 0;
    }

    @Override
    public int insert(Admin record) {
        return 0;
    }

    @Override
    public int insertSelective(Admin record) {
        return 0;
    }

    @Override
    public Admin selectByPrimaryKey(Integer aid) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Admin record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Admin record) {
        return 0;
    }

    @Override
    public int login(String name,String password) {


        try {
            Admin admin2 = adminMapper.login(name,password);
            //     Admin admin = adminMapper.selectByName(name);
            System.out.println(admin2.getName());
            System.out.println(admin2.getPassWord());
            if (name.equals(admin2.getName()) && password.equals(admin2.getPassWord())) {
                return 1;
            }else return 0;
        } catch (Exception e) {
            return 0;
        }

    }


    @Override
    public Admin  selectByTitle(String name){

        String key = "Admin_" + name;
        ValueOperations<String, Admin> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);

        if (hasKey) {
            long start = System.currentTimeMillis();
            Admin admin = operations.get(key);
            System.out.println("=== =======从缓存中获得数据=========");
            System.out.println(admin.getName());
            System.out.println("==============================");
            long end = System.currentTimeMillis();
            System.out.println("查询redis花费的时间是:" + (end - start)+"ms");
            return admin;
        } else {

            long start = System.currentTimeMillis();
            Admin admin = adminMapper.selectByTitle(name);
            System.out.println("==========从数据表中获得数据=========");
            System.out.println(admin.getName());
            System.out.println("==============================");


            // 写入缓存
            operations.set(key, admin, 5, TimeUnit.HOURS);
            long end = System.currentTimeMillis();
            System.out.println("查询mysql花费的时间是:" + (end - start)+"ms");
            return admin;


        }


    }








}
