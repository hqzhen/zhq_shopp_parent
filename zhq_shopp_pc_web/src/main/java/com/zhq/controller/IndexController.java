package com.zhq.controller;

import com.zhq.common.base.ResponseBase;
import com.zhq.common.constants.Constants;
import com.zhq.common.utils.CookieUtil;
import com.zhq.feign.MemberServiceFeign;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

/**
 * @program: zhq_shopp_parent
 * @description:
 * @author: HQ Zheng
 * @create: 2019-09-27 16:34
 */
@Controller
public class IndexController {

    //首页
    private static final String INDEX = "index";

    @Autowired
    private MemberServiceFeign memberServiceFeign;

    /**
     * 首页
     *
     * @return String
     */
    @GetMapping("/")
    public String index(HttpServletRequest request) {
        //1.从cookie中获取token
        String token = CookieUtil.getUid(request, Constants.COOKIE_MEMBER_TOKEN);
        //2.如果cookie存在token,调用会员服务接口，使用token查询用户查询信息
        if(!StringUtils.isBlank(token)){
            ResponseBase responseBase = memberServiceFeign.findByUserToken(token);
            if(responseBase.getCode().equals(Constants.HTTP_RES_CODE_200)){
                LinkedHashMap userData= (LinkedHashMap) responseBase.getData();
                String userName = (String) userData.get("userName");
                request.setAttribute("userName",userName);
            }
        }
        return INDEX;
    }
}
