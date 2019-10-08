package cn.dutyujm.feign;

import cn.dutyujm.pojo.Picture;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "baidu", url = "https://aip.baidubce.com/rest/2.0/face/v3/")
public interface FeignService {
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    Object getData(@RequestParam("access_token") String access_token, @RequestBody Picture picture);
}
