package com.review.controller;

import com.review.file.FastDFSFile;
import com.review.util.FastDFSClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 郑泽雄
 * @Description 文件上传类
 * @create 2020-10-01 14:22
 */
@RestController
public class FileController {
    /**
     * @param file 文件
     * @return 上传地址
     * @throws IOException IO异常抛出
     */
    @PostMapping(value = "/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        //封装一个FastDFSFile
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(),//文件名字
                file.getBytes(),            //文件字节数组
                StringUtils.getFilenameExtension(file.getOriginalFilename()));//文件扩展名
        //文件上传
        String[] uploads = FastDFSClient.uploadFile(fastDFSFile);
        //组装文件上传地址
        //return FastDFSClient.getTrackerUrl()+"/"+uploads[0]+"/"+uploads[1];   文件上传地址，先判断地址是否为空，防止乱跑
        return FastDFSClient.getTrackerUrl() + "/" + uploads[0] + "/" + uploads[1];
    }

    /**
     * @param file 文件
     * @return url
     * @throws IOException 异常抛出
     * @Description 附件上传，无需构建附件信息
     */
    @PostMapping("/uploads")
    public String uploads(MultipartFile file) throws IOException {
        //创建封装文件的对象信息
        //获取文件名字
        String name = file.getOriginalFilename();
        //获取文件内容
        byte[] content = file.getBytes();
        //获取文件扩展名
        String ext = FilenameUtils.getExtension(name);
        FastDFSFile fastDFSFile = new FastDFSFile(name, content, ext);
        //调用文件上传，只包含组名称+所在的远程服务器路径
        String[] result = FastDFSClient.uploadFile(fastDFSFile);
        //拼接该附件的完整路径
        String path = FastDFSClient.getTrackerUrl();
        //http://192.168.211.129:8080/
        return path + "/" + result[0] + "/" + result[1];
    }
}
