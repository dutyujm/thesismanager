package cn.dutyujm;

import cn.hutool.core.lang.Assert;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@EnableFeignClients

@MapperScan("cn.dutyujm.mapper")
public class AccountApplication {



    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class,args);
    }
}
