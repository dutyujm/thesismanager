package cn.dutyujm.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Result {
    private String face_token;
    private List<User> user_list;
}
