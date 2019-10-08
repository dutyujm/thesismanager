package cn.dutyujm.pojo;

import lombok.Data;

@Data
public class UserMatch {

    private Integer sid;

    private String name;

    private String phoneNumber;

    private String qq;

    private String email;

    private String photo;

   private float score;
}
