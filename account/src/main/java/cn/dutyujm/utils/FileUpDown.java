package cn.dutyujm.utils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.SftpException;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.websocket.Session;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

public class FileUpDown {
    Logger logger = LoggerFactory.getLogger(getClass());

    //linux服务器基本连接参数
    String host = "101.132.110.112";//ip
    String username = "root";//用户名
    String password = "wqD929967623";//密码
    int port = 22;

    ChannelSftp sftp = null;
    String remotePath = "/usr/java/tomcat/apache-tomcat-9.0.24/webapps/markdownpicture";//服务器上的目标路径
    String path = "/usr/java/tomcat/apache-tomcat-9.0.24/webapps/markdownpicture";//实际路径

    //连接服务器 获得ChannelSftp
    public ChannelSftp getSftp() {
        try {
            JSch jsch = new JSch();
            com.jcraft.jsch.Session session = jsch.getSession(username, host, port);
           session.setPassword(password);
            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking", "no");
            session.setConfig(properties);
            session.connect();
            Channel channel =  session.openChannel("sftp");
            channel.connect();
           sftp = (ChannelSftp) channel;
            logger.info(String.valueOf(sftp));
        } catch (Exception e) {
            System.out.println("连接失败");
            e.printStackTrace();
        }
        return sftp;
    }
    //上传文件
    public String upLoad(MultipartFile file, Integer sid) throws IOException, SftpException {
        InputStream is =null;
        String URL  = null;
        try{
            //要上传的文件名
            String filename = file.getOriginalFilename();
            String suffix = filename.substring(filename.lastIndexOf("."));
            //生成文件名
//            String autofilename = UUID.randomUUID().toString();
            String autofilename = sid.toString()+".png";
            //生成路径加文件名
            String path = remotePath + autofilename + suffix;
            path = "png23.png";
            logger.info(path);
            //得到文件输入流
            is = file.getInputStream();
            this.getSftp().put(is, autofilename);
            this.getSftp().cd("/usr/java/tomcat/apache-tomcat-9.0.24/webapps/markdownpicture/");
            BASE64Encoder base64Encoder =new BASE64Encoder();
            BASE64Encoder bEncoder=new BASE64Encoder();


            String base64EncoderImg=  bEncoder.encode(file.getBytes()).replaceAll("[\\s*\t\n\r]", "");
//            this.getSftp().put( is,path);

            File f = null;

            f=File.createTempFile("tmp", null);
            file.transferTo(f);
            f.deleteOnExit();     //使用完成删除文件

            FileInputStream inputFile = new FileInputStream(f);
            String base64=null;
            byte[] buffer = new byte[(int) f.length()];
            inputFile.read(buffer);
            inputFile.close();
            base64=new BASE64Encoder().encode(buffer);
            String cmd = "mv -f /root/"+autofilename+" /usr/java/tomcat/apache-tomcat-9.0.24/webapps/markdownpicture";
            //  this.getSftp().put("png23.png","/usr/java/tomcat/apache-tomcat-9.0.24/webapps/markdownpicture/");
            SshServerUtils.execCmd(cmd,"root","wqD929967623","101.132.110.112",22,"/");

            URL = "http://101.132.110.112:8080/markdownpicture/"+autofilename;

        }finally {
            //1.关闭输入流
            //2.断开连接
        }

        return URL;
    }




    public String search(MultipartFile file) throws IOException, SftpException {
        InputStream is =null;
        String URL  = null;
        try{
            //要上传的文件名
            String filename = file.getOriginalFilename();
            String suffix = filename.substring(filename.lastIndexOf("."));
            //生成文件名
            String autofilename = UUID.randomUUID().toString();
            //生成路径加文件名
            String path = remotePath + autofilename + suffix;

            //得到文件输入流
            is = file.getInputStream();
            this.getSftp().put(is, autofilename);
            BASE64Encoder bEncoder=new BASE64Encoder();


            String base64EncoderImg=  bEncoder.encode(file.getBytes()).replaceAll("[\\s*\t\n\r]", "");
//            this.getSftp().put( is,path);

            File f = null;

            f=File.createTempFile("tmp", null);
            file.transferTo(f);
            f.deleteOnExit();     //使用完成删除文件

            FileInputStream inputFile = new FileInputStream(f);
            String base64=null;
            byte[] buffer = new byte[(int) f.length()];
            inputFile.read(buffer);
            inputFile.close();
            base64=new BASE64Encoder().encode(buffer);
            String cmd = "mv -f /root/"+autofilename+" /usr/java/tomcat/apache-tomcat-9.0.24/webapps/searchpicture";
            //  this.getSftp().put("png23.png","/usr/java/tomcat/apache-tomcat-9.0.24/webapps/markdownpicture/");
            SshServerUtils.execCmd(cmd,"root","wqD929967623","101.132.110.112",22,"/");

            URL = "http://101.132.110.112:8080/searchpicture/"+autofilename;

        }finally {
            //1.关闭输入流
            //2.断开连接
        }

        return URL;
    }






}
//            String path = "../usr/java/file/img/";
