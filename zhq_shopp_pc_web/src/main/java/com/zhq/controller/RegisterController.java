package com.zhq.controller;

import com.zhq.api.entity.UserEntity;
import com.zhq.common.base.ResponseBase;
import com.zhq.common.constants.Constants;
import com.zhq.feign.MemberServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: zhq_shopp_parent
 * @description: 会员注册
 * @author: HQ Zheng
 * @create: 2019-09-27 17:04
 */
@Controller
public class RegisterController {

    @Autowired
    private MemberServiceFeign memberServiceFeign;
    private static final String LOGIN = "login";
    private static final String REGISTER = "register";

    @GetMapping("/register")
    public String register() {
        return REGISTER;
    }

    @PostMapping("/register")
    public String register(@RequestBody UserEntity user, HttpServletRequest request) {
        // 1.调用会员服务注册
        ResponseBase regUser = memberServiceFeign.register(user);
        if (!regUser.getCode().equals(Constants.HTTP_RES_CODE_200)) {
            request.setAttribute("error", regUser.getMsg());
            return REGISTER;
        }
        // 2.注册成功，跳转到登录页面
        return LOGIN;
    }


}
