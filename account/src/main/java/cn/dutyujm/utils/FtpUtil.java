package cn.dutyujm.utils;

import io.micrometer.core.instrument.util.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.tomcat.util.http.fileupload.util.Closeable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.MalformedURLException;

import static org.springframework.util.StringUtils.getFilename;


@Component
public class FtpUtil {

    Logger logger = LoggerFactory.getLogger(getClass());
    private String LOCAL_CHARSET = "GBK";

    //ftp服务器地址
    private String hostname = "101.132.110.112";

    //ftp服务器端口

    private int port = 21;

    //ftp登录账号
    private String username = "www";

    //ftp登录密码

    private String password = "DpEmvJXYkLka";

    //ftp保存目录

    private String basePath = "/root";


    /**
     * 初始化ftp服务器
     */
    public FTPClient getFtpClient() {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");

        try {
            ftpClient.setDataTimeout(1000 * 120);//设置连接超时时间
            logger.info("connecting...ftp服务器:" + hostname + ":" + port);
            ftpClient.connect(hostname,port); // 连接ftp服务器
            ftpClient.login(username, password); // 登录ftp服务器
            int replyCode = ftpClient.getReplyCode(); // 是否成功登录服务器
            if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(
                    "OPTS UTF8", "ON"))) {      // 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
                LOCAL_CHARSET = "UTF-8";
            }
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                logger.error("connect failed...ftp服务器:" + hostname + ":" + port);
            }
            logger.info("connect successfu...ftp服务器:" + hostname + ":" + port);
        } catch (MalformedURLException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return ftpClient;
    }


    /**
     * 上传文件
     *
     * @param targetDir    ftp服务保存地址
     * @param fileName    上传到ftp的文件名
     * @param inputStream 输入文件流
     * @return
     */
    public boolean uploadFileToFtp(String targetDir, String fileName, InputStream inputStream) {
        boolean isSuccess = false;
        String servicePath = String.format("%s%s%s", basePath, "/", targetDir);
//        String servicePath = "/usr/java/tomcat/apache-tomcat-9.0.24/webapps/markdownpicture";
        FTPClient ftpClient = getFtpClient();
        try {
            if (ftpClient.isConnected()) {
                logger.info("开始上传文件到FTP,文件名称:" + fileName);

                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置上传文件类型为二进制，否则将无法打开文件
                logger.info("2开始上传文件到FTP,文件名称:" + fileName);

                logger.info("996开始上传文件到FTP,文件名称:" +  ftpClient.makeDirectory(servicePath));
                logger.info("3开始上传文件到FTP,文件名称:" + fileName);
                ftpClient.changeWorkingDirectory(servicePath);
                logger.info("520开始上传文件到FTP,文件名称:" +   ftpClient.changeWorkingDirectory(servicePath));
                logger.info("4开始上传文件到FTP,文件名称:" + fileName);
                //设置为被动模式(如上传文件夹成功，不能上传文件，注释这行，否则报错refused:connect  )
//                ftpClient.enterLocalPassiveMode();//设置被动模式，文件传输端口设置
                logger.info("5开始上传文件到FTP,文件名称:" + fileName);
//                ftpClient.enterLocalActiveMode();
                ftpClient.storeFile(fileName, inputStream);
                logger.info("6开始上传文件到FTP,文件名称:" + fileName);
                inputStream.close();
                ftpClient.logout();
                isSuccess = true;
                logger.info(fileName + "文件上传到FTP成功");
            } else {
                logger.error("FTP连接建立失败");
            }
        } catch (Exception e) {
            logger.error(fileName + "文件上传到FTP出现异常");
            logger.error(e.getMessage(), e);
        } finally {
            closeFtpClient(ftpClient);
       //     closeStream(inputStream);
        }
        return isSuccess;
    }

    public void closeStream(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
//public boolean uploadFile(String ip, int port, String username,
//                          String password, String serverpath, String file) {
//    // 初始表示上传失败
//    boolean success = false;
//    // 创建FTPClient对象
//    FTPClient ftp = new FTPClient();
//    ftp.setControlEncoding("UTF-8");
//    try {
//        int reply=0;
//        // 连接FTP服务器
//        // 如果采用默认端口，可以使用ftp.connect(ip)的方式直接连接FTP服务器
//        ftp.connect(ip, port);
//        // 登录ftp
//        ftp.login(username, password);
//        // 看返回的值是不是reply>=200&&reply<300 如果是，表示登陆成功
//        reply = ftp.getReplyCode();
//        // 以2开头的返回值就会为真
//        if (!FTPReply.isPositiveCompletion(reply)) {
//            ftp.disconnect();
//            return success;
//        }
//        // 转到指定上传目录
//
//        serverpath=gbkToIso8859(serverpath);
//        ftp.changeWorkingDirectory(iso8859ToGbk(serverpath));
//
//        checkPathExist(ftp,iso8859ToGbk(serverpath));
//
//        //输入流
//        InputStream input=null;
//        try {
//            file=gbkToIso8859(file);
//            input = new FileInputStream(iso8859ToGbk(file));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        // 将上传文件存储到指定目录
//        file=iso8859ToGbk(file);
//        flag=ftp.storeFile(iso8859ToGbk(serverpath)+"/"+iso8859ToGbk(getFilename(file)), input);
//        // 关闭输入流
//        input.close();
//        // 退出ftp
//        ftp.logout();
//    } catch (IOException e) {
//        e.printStackTrace();
//    } finally {
//        if (ftp.isConnected()) {
//            try {
//                ftp.disconnect();
//            } catch (IOException ioe) {
//            }
//        }
//    }
//    return success;
//}

    //改变目录路径
    public boolean changeWorkingDirectory(FTPClient ftpClient, String directory) {
        boolean flag = true;
        try {
            flag = ftpClient.changeWorkingDirectory(directory);
            if (flag) {
                logger.info("进入文件夹" + directory + " 成功！");

            } else {
                logger.info("进入文件夹" + directory + " 失败！开始创建文件夹");
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return flag;
    }

    //创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
    public boolean CreateDirecroty(FTPClient ftpClient, String remote) throws IOException {
        boolean success = true;

        String directory = remote;
        if (!remote.endsWith(File.separator)) {
            directory = directory + File.separator;
        }
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase(File.separator) && !changeWorkingDirectory(ftpClient, new String(directory))) {
            int start = 0;
            int end = 0;
            if (directory.startsWith(File.separator)) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf(File.separator, start);
            String path = "";
            String paths = "";
            while (true) {
                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                path = path + File.separator + subDirectory;
                if (!existFile(ftpClient, path)) {
                    if (makeDirectory(ftpClient, subDirectory)) {
                        changeWorkingDirectory(ftpClient, subDirectory);
                    } else {
                        logger.error("创建目录[" + subDirectory + "]失败");
                        changeWorkingDirectory(ftpClient, subDirectory);
                    }
                } else {
                    changeWorkingDirectory(ftpClient, subDirectory);
                }

                paths = paths + File.separator + subDirectory;
                start = end + 1;
                end = directory.indexOf(File.separator, start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }

    //判断ftp服务器文件是否存在
    public boolean existFile(FTPClient ftpClient, String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }

    //创建目录
    public boolean makeDirectory(FTPClient ftpClient, String dir) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                logger.info("创建文件夹" + dir + " 成功！");

            } else {
                logger.info("创建文件夹" + dir + " 失败！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return flag;
    }

    /**
     * 下载文件 *
     *
     * @param pathName FTP服务器文件目录 *
     * @param pathName 下载文件的条件*
     * @return
     */
    public boolean downloadFile(FTPClient ftpClient, String pathName, String targetFileName, String localPath) {
        boolean flag = false;
        OutputStream os = null;
        try {
            System.out.println("开始下载文件");
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathName);
            ftpClient.enterLocalPassiveMode();
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for (FTPFile file : ftpFiles) {
                String ftpFileName = file.getName();
                if (targetFileName.equalsIgnoreCase(ftpFileName.substring(0, ftpFileName.indexOf(".")))) {
                    File localFile = new File(localPath);
                    os = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(file.getName(), os);
                    os.close();
                }
            }
            ftpClient.logout();
            flag = true;
            logger.info("下载文件成功");
        } catch (Exception e) {
            logger.error("下载文件失败");
            logger.error(e.getMessage(), e);
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return flag;
    }

    /*下载文件*/
    public InputStream download(String ftpFile, FTPClient ftpClient) throws IOException {
        String servicePath = String.format("%s%s%s", basePath, "/", ftpFile);
        logger.info("【从文件服务器获取文件流】ftpFile ： " + ftpFile);
        if (StringUtils.isBlank(servicePath)) {
            throw new RuntimeException("【参数ftpFile为空】");
        }
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.enterLocalPassiveMode();
        ftpFile = new String(servicePath.getBytes("utf-8"), "iso-8859-1");
        return ftpClient.retrieveFileStream(ftpFile);
    }

    /**
     * 删除文件 *
     *
     * @param pathname FTP服务器保存目录 *
     * @param filename 要删除的文件名称 *
     * @return
     */
    public boolean deleteFile(String pathname, String filename) {
        boolean flag = false;
        FTPClient ftpClient = getFtpClient();
        try {
            logger.info("开始删除文件");
            if (ftpClient.isConnected()) {
                //切换FTP目录
                ftpClient.changeWorkingDirectory(pathname);
                ftpClient.enterLocalPassiveMode();
                ftpClient.dele(filename);
                ftpClient.logout();
                flag = true;
                logger.info("删除文件成功");
            } else {
                logger.info("删除文件失败");

            }
        } catch (Exception e) {
            logger.error("删除文件失败");
            logger.error(e.getMessage(), e);
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return flag;
    }

    public void closeFtpClient(FTPClient ftpClient) {
        if (ftpClient.isConnected()) {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public InputStream downloadFile(FTPClient ftpClient, String pathname, String filename) {
        InputStream inputStream = null;
        try {
            System.out.println("开始下载文件");
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.enterLocalPassiveMode();
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for (FTPFile file : ftpFiles) {
                if (filename.equalsIgnoreCase(file.getName())) {
                    inputStream = ftpClient.retrieveFileStream(file.getName());
                    break;
                }
            }
            ftpClient.logout();
            logger.info("下载文件成功");
        } catch (Exception e) {
            logger.error("下载文件失败");
            logger.error(e.getMessage(), e);
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return inputStream;
    }
}

