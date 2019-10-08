package cn.dutyujm.vo;

import cn.dutyujm.pojo.Voteoptions;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Data
public class VoteVo {
    private String title;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",   timezone="GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
    private Date starttime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endtime;

    private Integer sum=0 ;

    private List<Voteoptions> options;

}
