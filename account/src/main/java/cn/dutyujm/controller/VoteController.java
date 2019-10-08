package cn.dutyujm.controller;

import cn.dutyujm.pojo.Uservote;
import cn.dutyujm.pojo.Vote;
import cn.dutyujm.pojo.Voteoptions;
import cn.dutyujm.service.VoteService;
import cn.dutyujm.service.VoteoptionsService;
import cn.dutyujm.vo.SearchVoteVo;
import cn.dutyujm.vo.ViewDetailVo;
import cn.dutyujm.vo.VoteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/Vote")
public class VoteController {
    @Autowired
    private VoteService voteService;
    @Autowired
    private VoteoptionsService voteoptionsService;

    /**
     * 找出所有投票
     * @return
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public List<Vote> student(){
        return voteService.findAll();
    }


    /**
     * 新建投票
     * @param vote
     * @return
     */
    @RequestMapping(value = "/addVote",method = RequestMethod.POST)
    public Integer  addVote(@RequestBody Vote vote){
        vote.setStarttime(new Date());
        return voteService.insert(vote);

    }

    /**
     * 删除投票的接口
     * @param vid
     * @return
     */
    @RequestMapping(value = "/deleteVote",method = RequestMethod.POST)
    public Integer deleteVote(@RequestParam("vid") Integer vid){
        return voteService.deleteByPrimaryKey(vid);
    }


    /**
     * 单选投票的接口
     * @param vid
     * @param sid
     * @param optionid
     * @return
     */
    @RequestMapping(value = "/oneChoice",method = RequestMethod.POST)
    public Integer oneChoice(@RequestParam("vid") Integer vid,@RequestParam("sid") Integer sid,@RequestParam("optionid") Integer optionid){
        return voteService.oneChoice(vid,sid,optionid);
    }


    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public Integer oneChoice(@RequestBody Voteoptions voteoptions){
        return voteoptionsService.insert(voteoptions);
    }

    /**
     * 找出我发布的投票
     * @param authorsid
     * @return
     */
    @GetMapping(value = "/selectMyVote")
    public  List<Vote> selectMyVote(@RequestParam("authorsid") Integer authorsid){
        return voteService.selectMyVote(authorsid);
    }

    /**
     * 查看投票详情
     * @param vid
     * @return
     */
    @GetMapping(value = "/viewDetail")
    public List<ViewDetailVo> viewDetail(@RequestParam("vid") Integer vid){
        return voteService.viewDetail(vid);
    };


    /**
     * 模糊查询所有投票
     * @param sid
     * @param title
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "/searchVote")
    public   List<SearchVoteVo> searchVote(@RequestParam("sid")Integer sid,
                                           @RequestParam("title") String title ,
                                           @RequestParam(defaultValue = "1") Integer page ,
                                           @RequestParam(defaultValue = "10") Integer limit){
        return voteService.searchVote(sid, title, page, limit);
    }



    @RequestMapping(value = "/multipleChoice",method = RequestMethod.POST)
    public Integer multipleChoice(@RequestParam("sid") Integer sid,@RequestBody List<Uservote> uservoteList ){
        return voteService.multipleChoice(sid,uservoteList);

//        for (int i = 0; i < uservoteList.size(); i++){
//            Uservote uservote =  uservoteList.get(i);
//            System.out.println(uservote.getVid());
//
//        }
//        return 1;
    }

}
