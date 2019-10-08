package cn.dutyujm.service;

import cn.dutyujm.pojo.Admin;
import cn.dutyujm.pojo.User;
import com.jcraft.jsch.SftpException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    int deleteByPrimaryKey(Integer sid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    int updateBySid(User record);
    int login(String sid,String password);
    List<User> selectUserByName(String title,Integer page, Integer limit);
    int deleteBySid (Integer sid);

    String updatePhoto(MultipartFile file);

    String sftpupdatePhoto(MultipartFile file, Integer sid) throws IOException, SftpException;

    User selectBySid(Integer id );
    User selectBySid2(Integer id );

    String search(MultipartFile file) throws IOException, SftpException;

    int countSelectUserByName(String title);
    List<User> selectUserByName2(String title,Integer page, Integer limit);
}
