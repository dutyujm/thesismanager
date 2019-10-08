package cn.dutyujm.feign;

import cn.dutyujm.pojo.Admin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "account")
public interface AccountFeign {

    @RequestMapping(value = "/admin/login",method = RequestMethod.POST)
     Admin login(@RequestParam("name") String name, @RequestParam("password") String password);


}
