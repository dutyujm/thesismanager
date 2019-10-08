package cn.dutyujm.service.impl;

import cn.dutyujm.mapper.RequestMapper;
import cn.dutyujm.pojo.Request;
import cn.dutyujm.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Raymond
 * @date: 2019/9/7
 * @Description:
 **/
@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestMapper requestMapper;

    @Override
    public int deleteByPrimaryKey(Integer rid) {
        return 0;
    }

    @Override
    public int insert(Request record) {
        return 0;
    }

    @Override
    public int insertSelective(Request record) {
        return 0;
    }

    @Override
    public Request selectByPrimaryKey(Integer rid) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Request record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Request record) {
        return 0;
    }
}
