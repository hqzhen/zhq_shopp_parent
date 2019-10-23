package com.zhq.controller;

import com.zhq.common.base.ResponseBase;
import com.zhq.common.constants.Constants;
import com.zhq.feign.CallBackServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: zhq_shopp_parent
 * @description: 支付宝回调服务
 * @author: HQ Zheng
 * @create: 2019-10-23 10:21
 */
@Slf4j
@Controller
@RequestMapping("/alibaba/callBack")
public class CallBackController {

    @Autowired
    private CallBackServiceFeign callBackServiceFeign;

    //同步通知
    @RequestMapping("/returnUrl")
    public void synCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        //获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        log.info("#####支付宝同步回调CallBackController###synCallBack()开始params:{}", params);
        ResponseBase responseBase = callBackServiceFeign.synCallBack(params);
        if (!responseBase.getCode().equals(Constants.HTTP_RES_CODE_200)) {
            log.info("#####支付宝同步回调CallBackController###synCallBack()错误params:{}", params);
            return;
        }
        LinkedHashMap data = (LinkedHashMap) responseBase.getData();
        //商户订单号
        String outTradeNo = (String) data.get("outTradeNo");
        //支付宝交易号
        String tradeNo = (String) data.get("tradeNo");
        //付款金额
        String totalAmount = (String) data.get("totalAmount");
        request.setAttribute("outTradeNo", outTradeNo);
        request.setAttribute("tradeNo", tradeNo);
        request.setAttribute("totalAmount", totalAmount);
        log.info("#####支付宝同步回调CallBackController###synCallBack()结束params:{}", params);
        //封装成html 浏览器模拟提交
        String htmlForm="<form name='punchout_form' method='post' " +
                "action='http://127.0.0.1/alibaba/callBack/synSuccessPage' >" +
                "<input type='hidden' name='outTradeNo' value='"+outTradeNo+"'>" +
                "<input type='hidden' name='tradeNo' value='"+tradeNo+"'>" +
                "<input type='hidden' name='totalAmount' value='"+totalAmount+"'>" +
                "<input type='submit' value='立即支付' style='display:none'>" +
                "</form>" +
                "<script>document.forms[0].submit();</script>";
        PrintWriter writer = response.getWriter();
        writer.println(htmlForm);
        writer.close();
    }

    //以post表单隐藏参数
    @PostMapping("/synSuccessPage")
    public String synSuccessPage(String outTradeNo,String tradeNo,String totalAmount,HttpServletRequest request) {
        request.setAttribute("outTradeNo", outTradeNo);
        request.setAttribute("tradeNo", tradeNo);
        request.setAttribute("totalAmount", totalAmount);
        return "成功页面";
    }

    //异步通知
    @RequestMapping("/notifyUrl")
    @ResponseBody
    public String asynCallBack(@RequestParam Map<String, String> params) {
        return "success";
    }
}
