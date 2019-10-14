package com.zhq.api.service;

import com.zhq.api.entity.PaymentInfo;
import com.zhq.common.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/pay")
public interface PayService {

    /**
     * 创建支付令牌
     * @param paymentInfo 订单信息
     * @return ResponseBase
     */
    @RequestMapping("/createPayToken")
    ResponseBase createToken(@RequestBody PaymentInfo paymentInfo);

    /**
     * 查找订单通过支付令牌
     * @param payToken 支付令牌
     * @return ResponseBase
     */
    @RequestMapping("/findPayToken")
    ResponseBase findPayToken(@RequestParam("payToken") String payToken);
}
