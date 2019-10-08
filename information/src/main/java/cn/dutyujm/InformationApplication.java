package cn.dutyujm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@MapperScan("cn.dutyujm.mapper")
public class InformationApplication {
    public static void main(String[] args) {
        SpringApplication.run(InformationApplication.class,args);
    }
}
