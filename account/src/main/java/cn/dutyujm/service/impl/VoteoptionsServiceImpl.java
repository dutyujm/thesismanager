package cn.dutyujm.service.impl;

import cn.dutyujm.mapper.VoteoptionsMapper;
import cn.dutyujm.pojo.Vote;
import cn.dutyujm.pojo.Voteoptions;
import cn.dutyujm.service.VoteService;
import cn.dutyujm.service.VoteoptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteoptionsServiceImpl implements VoteoptionsService {
    @Autowired
    private VoteoptionsMapper voteoptionsMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Voteoptions record) {
        return voteoptionsMapper.insert(record);

    }

    @Override
    public int insertSelective(Voteoptions record) {
        return 0;
    }

    @Override
    public Voteoptions selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Voteoptions record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Voteoptions record) {
        return 0;
    }

    @Override
    public   List<Voteoptions> getOptionList (Integer vid){
        return voteoptionsMapper.getOptionList(vid);
    }

}
