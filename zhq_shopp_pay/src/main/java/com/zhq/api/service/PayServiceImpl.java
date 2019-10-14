package com.zhq.api.service;

import com.zhq.api.dao.PaymentInfoDao;
import com.zhq.api.entity.PaymentInfo;
import com.zhq.common.base.BaseApiService;
import com.zhq.common.base.ResponseBase;
import com.zhq.common.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: zhq_shopp_parent
 * @description:
 * @author: HQ Zheng
 * @create: 2019-10-14 21:08
 */
@RestController
public class PayServiceImpl extends BaseApiService implements  PayService {
    @Autowired
    private PaymentInfoDao paymentInfoDao;
    /**
     * 创建支付令牌
     * @param paymentInfo 订单信息
     * @return ResponseBase
     */
    @Override
    public ResponseBase createToken(@RequestBody PaymentInfo paymentInfo) {
        //1、创建支付请求信息
        Integer payId = paymentInfoDao.savePaymentType(paymentInfo);
        //2、生成对应的token
        if(payId<=0){
            return setResultError("创建支付订单失败！");
        }
        String payToken = TokenUtils.getPayToken();
        return null;
    }

    /**
     * 使用令牌查找支付信息
     * @param payToken 支付令牌
     * @return ResponseBase
     */
    @Override
    public ResponseBase findPayToken(@RequestParam String payToken) {
        return null;
    }
}
