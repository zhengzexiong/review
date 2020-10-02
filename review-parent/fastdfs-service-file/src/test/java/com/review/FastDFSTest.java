package com.review;

import com.review.util.FastDFSClient;
import org.apache.commons.io.IOUtils;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.FileOutputStream;

/**
 * @author 郑泽雄
 * @Description 文件下载测试
 * @create 2020-10-02 15:00
 */
public class FastDFSTest {
    //文件下载
    @Test
    public void testDownloadFile() throws Exception {
        String group_name = "group1";
        String remote_filename = "M00/00/00/wKjTgV92zM-AVOhAAAD-gLyscRo347.png";
        byte[] bytes = FastDFSClient.downloadFile(group_name, remote_filename);
        IOUtils.write(bytes, new FileOutputStream("d:/1.png"));
        System.out.println("下载成功");

    }

    //删除文件
    @Test
    public void testDeleteFile() {
        String group_name = "group1";
        String remote_filename = "M00/00/00/wKjTgV92zM-AVOhAAAD-gLyscRo347.png";
        FastDFSClient.deleteFile(group_name, remote_filename);
    }

    //获取文件信息
    @Test
    public void testFileInfo() {
        String group_name = "group1";
        String remote_filename = "M00/00/00/wKjTgV92zM-AVOhAAAD-gLyscRo347.png";
        FileInfo fileInfo = FastDFSClient.getFileInfo(group_name, remote_filename);
        System.out.println("文件创建日期：" + fileInfo.getCreateTimestamp());
        System.out.println("文件大小：" + fileInfo.getFileSize());
        System.out.println("文件所在服务器地址" + fileInfo.getSourceIpAddr());
    }

    //获取存储服务器信息
    @Test
    public void testStorageServer() {
        StorageServer group1 = FastDFSClient.getStorage("group1");
        String hostAddress = group1.getInetSocketAddress().getAddress().getHostAddress();
        int port = group1.getInetSocketAddress().getPort();
        int storePathIndex = group1.getStorePathIndex();
        System.out.println("存储服务器地址：" + hostAddress);
        System.out.println("存储服务器端口：" + port);
        System.out.println("存储服务器角标：" + storePathIndex);
    }

    //获取多个存储服务器信息
    @Test
    public void testServerInfo() {
        ServerInfo[] groups = FastDFSClient.getStorages("group1", "wKjTgV92zM-AVOhAAAD-gLyscRo347.png");
        ServerInfo group = groups[0];
        String ip = group.getIpAddr();
        int port = group.getPort();
        System.out.println("存储服务器地址：" + ip);
        System.out.println("存储服务器端口：" + port);
    }
}
