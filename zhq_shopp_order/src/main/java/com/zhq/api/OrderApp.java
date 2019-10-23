package com.zhq.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: zhq_shopp_parent
 * @description:
 * @author: HQ Zheng
 * @create: 2019-10-23 16:09
 */
@EnableEurekaClient
@SpringBootApplication
public class OrderApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class,args);
    }
}
