package cn.dutyujm.service;

import cn.dutyujm.pojo.Uservote;
import cn.dutyujm.pojo.Vote;
import cn.dutyujm.pojo.Voteoptions;
import cn.dutyujm.vo.SearchVoteVo;
import cn.dutyujm.vo.ViewDetailVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface VoteService {
    int deleteByPrimaryKey(Integer vid);

    int insert(Vote record);

    int insertSelective(Vote record);

    Vote selectByPrimaryKey(Integer vid);

    int updateByPrimaryKeySelective(Vote record);

    int updateByPrimaryKey(Vote record);

    List<Vote> findAll();

    int  oneChoice(Integer vid, Integer sid,  Integer optionid) ;

    List<Vote> selectMyVote(Integer authorsid);

    List<ViewDetailVo> viewDetail(Integer vid);

    List<SearchVoteVo> searchVote(Integer sid, String title , Integer page , Integer limit);

    Integer multipleChoice( Integer sid, List<Uservote> uservoteList );
}
