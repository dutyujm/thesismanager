package cn.dutyujm.controller;

import cn.dutyujm.pojo.Voteoptions;
import cn.dutyujm.service.VoteoptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/Voteoptions")
public class VoteoptionsController {


    @Autowired
    private VoteoptionsService voteoptionsService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public Integer test(){
        return 1;
    }

        @RequestMapping(value = "/insertList",method = RequestMethod.POST)
        public Integer student(@RequestBody List<Voteoptions> voteoptionsList){


            for (int i = 0; i < voteoptionsList.size(); i++){
                Voteoptions voteoption =  voteoptionsList.get(i);
                voteoptionsService.insert(voteoption);
            }
            return 1;
        }

        @RequestMapping(value = "/getOptionList",method = RequestMethod.GET)
         public List<Voteoptions> getOptionList(@RequestParam Integer vid){
         return    voteoptionsService.getOptionList(vid);
        }

//        @RequestMapping(value = "/chooseOptions",method = RequestMethod.POST)
//    public Integer  chooseOptions(@RequestParam Integer vid){
//
//        }






}
