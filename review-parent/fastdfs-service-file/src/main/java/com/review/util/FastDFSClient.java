package com.review.util;

import com.review.file.FastDFSFile;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
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
            //初始化FastDFS配置信息，获取连接信息
            ClientGlobal.init(config_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fastDFSFile 附件信息
     * @return uploadResult.string[]        图片地址
     * @Description 附件上传
     */
    public static String[] uploadFile(FastDFSFile fastDFSFile) {
        try {
            byte[] file_buff = fastDFSFile.getContent();        // 附件内容
            String file_ext_name = fastDFSFile.getExt();         // 附件扩展名称
            NameValuePair[] meta_list = new NameValuePair[1];   // 附件备注
            meta_list[0] = new NameValuePair(fastDFSFile.getAuthor());

            // 创建跟踪服务器的客户端
            TrackerClient trackerClient = new TrackerClient();
            // 由该客户端获取服务器端
            TrackerServer trackerServer = trackerClient.getConnection();
            // 创建存储服务器客户端
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 附件上传操作
            // 组名：/group1
            // 附件名称：/M00/00/00/wKjThF0DBzaAP23MAAXz2mMp9oM26.jpeg
            String[] result = storageClient.upload_file(file_buff, file_ext_name, meta_list);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return String
     * @Description 获取tracker server服务器地址
     */
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

    /**
     * @param group_name      组名
     * @param remote_filename 所在服务器路径
     * @return byte[]
     * @Description 文件下载
     */
    public static byte[] downloadFile(String group_name, String remote_filename) {
        try {
            //创建跟踪服务器的客户端
            TrackerClient trackerClient = new TrackerClient();
            //由该客户端获取服务端
            TrackerServer trackerServer = trackerClient.getConnection();
            //创建存储服务器客户端
            StorageClient storageClient = new StorageClient(trackerServer, null);
            //文件下载
            return storageClient.download_file(group_name, remote_filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param group_name      组名
     * @param remote_fileName 所在服务器的路径
     * @Description 文件删除
     */
    public static void deleteFile(String group_name, String remote_fileName) {
        try {
            //创建跟踪服务器的客户端
            TrackerClient trackerClient = new TrackerClient();
            //获取跟踪服务器服务端
            TrackerServer trackerServer = trackerClient.getConnection();
            //创建存储服务器客户端
            StorageClient storageClient = new StorageClient(trackerServer, null);
            //文件删除
            storageClient.delete_file(group_name, remote_fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param group_name      组名
     * @param remote_filename 文件名
     * @return 返回文件信息
     * @Description 获取文件信息
     */
    public static FileInfo getFileInfo(String group_name, String remote_filename) {
        try {
            //创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            //获取跟踪服务器服务端
            TrackerServer trackerServer = trackerClient.getConnection();
            //创建存储服务器客户端
            StorageClient storageClient = new StorageClient(trackerServer, null);
            //返回附件信息
            return storageClient.get_file_info(group_name, remote_filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param groupName 组名
     * @return 返回存储服务器嘻嘻
     * @Description 获取单个存储服务器信息
     */
    public static StorageServer getStorage(String groupName) {
        try {
            //创建跟踪服务器的客户端
            TrackerClient trackerClient = new TrackerClient();
            //由该客户端获取服务端
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取存储服务器信息
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer, groupName);
            //集群
            //trackerClient.getFetchStorage();
            return storageServer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ServerInfo[] getStorages(String groupName, String filename) {
        try {
            //创建跟踪服务器的客户端
            TrackerClient trackerClient = new TrackerClient();
            //由该客户端获取服务端
            TrackerServer trackerServer = trackerClient.getConnection();
            //返回存储服务器信息
            return trackerClient.getFetchStorages(trackerServer, groupName, filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
