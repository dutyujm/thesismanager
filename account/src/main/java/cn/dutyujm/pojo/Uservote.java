package cn.dutyujm.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Uservote {
    private Integer id;

    private Integer sid;

    private Integer optionid;

    private Integer vid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getOptionid() {
        return optionid;
    }

    public void setOptionid(Integer optionid) {
        this.optionid = optionid;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    @JsonCreator
    public Uservote(@JsonProperty Integer sid,@JsonProperty Integer vid ,@JsonProperty Integer optionid) {
        this.sid = sid;
        this.vid = vid;
        this.optionid = optionid;
    }
    @JsonCreator
    public Uservote() {

    }
}