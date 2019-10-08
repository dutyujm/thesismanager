package cn.dutyujm.controller;

import cn.dutyujm.pojo.Thesis;
import cn.dutyujm.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: Raymond
 * @date: 2019/9/7
 * @Description:
 **/

@RestController
@RequestMapping("/thesis")
public class ThesisController {

    @Autowired
    private ThesisService thesisService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Thesis> getThesisList(HttpServletResponse response) {
        // 解决跨域请求问题
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        return thesisService.getThesisList();
    }

    @RequestMapping(value = "/findThesis",method = RequestMethod.GET )
    public Thesis  findThesisBySid(@RequestParam("sid")Integer sid){
        return thesisService.selectThesisBySid(sid);
    }

    @RequestMapping(value = "/findThesisByname",method = RequestMethod.POST)
    public List<Thesis> findThesisByname(@RequestParam("title") String title){
        return thesisService.selectThesisByName(title);
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public List<Thesis> pageThesis(@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "10") Integer limit){
            return thesisService.pageThesis(page,limit);
    }
}
