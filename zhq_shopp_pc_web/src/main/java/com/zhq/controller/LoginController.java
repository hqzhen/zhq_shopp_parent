package com.zhq.controller;

import com.zhq.api.entity.UserEntity;
import com.zhq.common.base.ResponseBase;
import com.zhq.common.constants.Constants;
import com.zhq.common.utils.CookieUtil;
import com.zhq.feign.MemberServiceFeign;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;

/**
 * @program: zhq_shopp_parent
 * @description: 登入服务
 * @author: HQ Zheng
 * @create: 2019-09-27 21:43
 */
@Controller
public class LoginController {
    @Autowired
    private MemberServiceFeign memberServiceFeign;
    private static final String INDEX="index";
    private static final String LOGIN = "login";

    @GetMapping("/login")
    public String login() {
        return LOGIN;
    }

    @PostMapping("/login")
    public String login(UserEntity userEntity, HttpServletRequest request, HttpServletResponse response) {
        ResponseBase responseBase = memberServiceFeign.login(userEntity);
        if (!responseBase.getCode().equals(Constants.HTTP_RES_CODE_200)){
            request.setAttribute("error","登入失败！账号或者密码错误");
            return LOGIN;
        }
        LinkedHashMap data = (LinkedHashMap) responseBase.getData();
        String userToken= (String) data.get("userToken");
        if(StringUtils.isBlank(userToken)){
            request.setAttribute("error","会话已失效！");
            return LOGIN;
        }
        CookieUtil.addCookie(response,Constants.COOKIE_MEMBER_TOKEN,userToken,Constants.COOKIE_TOKEN_TIMEOUT);
        return INDEX;
    }
}
