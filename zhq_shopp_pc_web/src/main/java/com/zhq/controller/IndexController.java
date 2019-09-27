package com.zhq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: zhq_shopp_parent
 * @description:
 * @author: HQ Zheng
 * @create: 2019-09-27 16:34
 */
@Controller
public class IndexController {

    //首页
    private static final String INDEX="index";

    /**
     * 首页
     * @return String
     */
    @GetMapping("/")
    public String index(){
     return  INDEX;
    }
}
