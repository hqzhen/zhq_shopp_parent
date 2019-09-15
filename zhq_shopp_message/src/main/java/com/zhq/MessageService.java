package com.zhq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: zhq_shopp_parent
 * @description: 项目启动类
 * @author: HQ Zheng
 * @create: 2019-09-15 21:45
 */
@SpringBootApplication
@EnableEurekaClient
public class MessageService {
    public static void main(String[] args) {
        SpringApplication.run(MessageService.class,args);
    }
}
