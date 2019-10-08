package cn.dutyujm.service.impl;

import cn.dutyujm.mapper.UserMapper;
import cn.dutyujm.pojo.User;
import cn.dutyujm.service.UserService;
import cn.dutyujm.utils.FileUpDown;
import cn.dutyujm.utils.FtpUtil;
import com.jcraft.jsch.SftpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service

public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public  int deleteByPrimaryKey(Integer sid){
        return 0;
    }

    @Override
    public  int insert(User record){
        userMapper.insert(record);
        return record.getId();
    }

    @Override
    public int insertSelective(User record){
        return 0;
    }

    @Override
    public User selectByPrimaryKey(Integer id){
        return null;
    }
    @Override

    public  int updateByPrimaryKeySelective(User record){
        return  0;
    }

    @Override
    public   int updateByPrimaryKey(User record){
        return userMapper.updateByPrimaryKey(record);
    }

    @Override

    public int login(String sid,String password){
       try {
           User user =    userMapper.selectBySid(Integer.parseInt(sid));

           if (Integer.parseInt(sid)==user.getSid()&&password.equals(user.getPassWord())){
               return 1;
           }else return 0;
       }
       catch (Exception e){
           return 0;
       }
    }

    @Override
    public List<User> selectUserByName(String title,Integer page, Integer limit){
        int tmp = (page-1)*limit;
        return    userMapper.selectUserByName(title,tmp,limit);
    }

    @Override
    public List<User> selectUserByName2(String title,Integer page, Integer limit){

        String key = "User_"+title+"_"+page.toString()+"_"+limit.toString();
//        ValueOperations<String, List<User>> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);

        if (hasKey) {
            long start = System.currentTimeMillis();

//            List<User> user = operations.get(key);
            List<User> user = redisTemplate.opsForList().range(key,0,-1);
            System.out.println("==========从缓存中获得数据=========");

            long end = System.currentTimeMillis();
            System.out.println("查询redis花费的时间是:" + (end - start)+"ms");
            return user;
        }else{

            long start = System.currentTimeMillis();
                int tmp = (page-1)*limit;
            List<User> user = userMapper.selectUserByName(title,tmp,limit);

            System.out.println("==========从数据表中获得数据=========");

            // 写入缓存
            redisTemplate.opsForList().rightPushAll(key,user);
//            operations.set(key,user);
//            operations.set(key, user, 5, TimeUnit.HOURS);
            long end = System.currentTimeMillis();
            System.out.println("查询mysql花费的时间是:" + (end - start)+"ms");


            return user;
        }






    }





    @Override
    public    int deleteBySid (Integer sid){
       return userMapper.deleteBySid(sid);
    }

    @Override
    public String updatePhoto(MultipartFile file) {
        //给图片起一个新的名称，防止在图片名称重复
        String newname=new String();

        if(file!=null){
            //生成新的图片名称
            newname = file.getOriginalFilename();
            try {
                //图片上传，调用ftp工具类 image 上传的文件夹名称，newname 图片名称，inputStream
                FtpUtil ftpUtil= new FtpUtil();
                boolean hopson = ftpUtil.uploadFileToFtp("image", newname, file.getInputStream());
                if(hopson) {

                }
            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            }
        }
        return "sucess";
    }

    @Override
    public String sftpupdatePhoto(MultipartFile file, Integer sid) throws IOException, SftpException {
       FileUpDown fileUpDown = new FileUpDown();

        return   fileUpDown.upLoad(file,  sid);


    }

    @Override
    public User selectBySid(Integer sid){
      return   userMapper.selectBySid(sid);

    }
    @Override
    public User selectBySid2(Integer sid){
        return   userMapper.selectBySid2(sid);

    }


    @Override
     public  String search(MultipartFile file) throws IOException, SftpException {
        FileUpDown fileUpDown = new FileUpDown();
        return fileUpDown.search(file);
    }

    @Override
    public int updateBySid(User record){
        return userMapper.updateBySid(record);
    }


@Override
public int countSelectUserByName(String title){
        return userMapper.countSelectUserByName(title);
}



}
