package com.review.util;

import com.review.file.FastDFSFile;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author 郑泽雄
 * @Description 封装FastDFS的api工具类
 * @create 2020-10-01 13:56
 */
public class FastDFSClient {
    /*
      @Description 初始化FastDFS配置
     */
    static {
        String path = "fdfs_client.conf";
        String config_name = new ClassPathResource(path).getPath();
        try {
            ClientGlobal.init(config_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fastDFSFile 附件信息
     * @return uploadResult.string[]
     */
    public static String[] uploadFile(FastDFSFile fastDFSFile) {
        try {
            //获取文件相关属性
            byte[] file_buff = fastDFSFile.getContent();
            String ext_name = fastDFSFile.getExt();
            NameValuePair[] meta_list = new NameValuePair[1];
            meta_list[0] = new NameValuePair(fastDFSFile.getAuthor());
            //1. 创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            //2. 获取跟踪服务器
            TrackerServer trackerServer = trackerClient.getConnection();
            //3. 创建存储服务器客户端
            StorageClient storageClient = new StorageClient(trackerServer, null);
            //4. 文件上传
            return storageClient.upload_file(file_buff, ext_name, meta_list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTrackerUrl() {
        try {
            //1. 创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            //2. 获取跟踪服务器
            TrackerServer trackerServer = trackerClient.getConnection();
            //2. 获取跟踪服务器地址
            String hostAddress = trackerServer.getInetSocketAddress().getAddress().getHostAddress();
            int port = ClientGlobal.getG_tracker_http_port();
            return "http://" + hostAddress + ":" + port;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
