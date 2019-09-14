package com.zhq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: zhq_shopp_parent
 * @description: 会员服务启动类
 * @author: HQ Zheng
 * @create: 2019-09-14 21:33
 */
@SpringBootApplication
@EnableEurekaClient
public class MemberServer {
    public static void main(String[] args) {
        SpringApplication.run(MemberServer.class,args);
    }
}
