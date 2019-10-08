package cn.dutyujm.service.impl;

import cn.dutyujm.mapper.UservoteMapper;
import cn.dutyujm.mapper.VoteMapper;
import cn.dutyujm.pojo.Uservote;
import cn.dutyujm.pojo.Vote;
import cn.dutyujm.pojo.Voteoptions;
import cn.dutyujm.service.VoteService;
import cn.dutyujm.vo.SearchVoteVo;
import cn.dutyujm.vo.ViewDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class VoteServiceImpl implements VoteService {


    @Autowired
    private VoteMapper voteMapper;



    @Autowired
    private UservoteMapper uservoteMapper;

    @Override
    public int deleteByPrimaryKey(Integer vid) {
        return voteMapper.deleteByPrimaryKey(vid);
    }

    @Override
    public int insert(Vote record) {
         voteMapper.insert(record);
        return record.getVid();
    }

    @Override
    public int insertSelective(Vote record) {
return 0;
    }

    @Override
    public Vote selectByPrimaryKey(Integer vid) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Vote record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Vote record) {
        return 0;
    }

    @Override
    public List<Vote> findAll(){
        return voteMapper.findAll();
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public int  oneChoice(Integer vid, Integer sid,  Integer optionid) {
         voteMapper.oneChoice(vid,sid,optionid);
        return 1;

    }

    @Override
    public  List<Vote> selectMyVote(Integer authorsid){
        return voteMapper.selectMyVote(authorsid);
    }
    @Override
    public List<ViewDetailVo> viewDetail(Integer vid){
        return voteMapper.viewDetail(vid);
    };

    @Override
    public  List<SearchVoteVo> searchVote(Integer sid, String title , Integer page , Integer limit){
        int begin = (page-1)*limit;
        return voteMapper.searchVote(sid,title,begin,limit);
    }

    @Override
    public Integer multipleChoice( Integer sid,  List<Uservote> uservoteList ){

        return voteMapper.multipleChoice(sid,uservoteList);
    }

}
