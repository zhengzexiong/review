package com.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author 郑泽雄
 * @Description
 * @create 2020-10-01 13:48
 */
//需要排除自动装配数据库
@SpringBootApplication(exclude = (DataSourceAutoConfiguration.class))
//@EnableEurekaClient
public class FileApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class,args);
    }
}
