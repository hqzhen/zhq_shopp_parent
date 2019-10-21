package com.zhq.controller;

import com.zhq.common.base.ResponseBase;
import com.zhq.common.constants.Constants;
import com.zhq.feign.PayServiceFeign;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;

/**
 * @program: zhq_shopp_parent
 * @description: 支付
 * @author: HQ Zheng
 * @create: 2019-10-21 20:28
 */
@Controller
public class PayController {

    @Autowired
    private PayServiceFeign payServiceFeign;

    /**
     * 使用token进行支付
     *
     * @param payToken 支付token
     */
    @RequestMapping("/aliPay")
    public void aliPay(String payToken, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if (StringUtils.isBlank(payToken)) {
            writer.println("参数为空");
            writer.close();
            return;
        }
        ResponseBase responseBase = payServiceFeign.findPayToken(payToken);
        if (!responseBase.getCode().equals(Constants.HTTP_RES_CODE_200)) {
            String msg = responseBase.getMsg();
            writer.println(msg);
            writer.close();
            return;
        }
        LinkedHashMap data = (LinkedHashMap) responseBase.getData();
        String payHtml = (String) data.get("payHtml");
        writer.println(payHtml);
        writer.close();
    }
}
