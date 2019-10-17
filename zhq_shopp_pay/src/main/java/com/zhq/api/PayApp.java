package com.zhq.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: zhq_shopp_parent
 * @description:
 * @author: HQ Zheng
 * @create: 2019-10-17 20:42
 */
@EnableEurekaClient
@SpringBootApplication
public class PayApp {
    public static void main(String[] args) {
        SpringApplication.run(PayApp.class,args);
    }
}
