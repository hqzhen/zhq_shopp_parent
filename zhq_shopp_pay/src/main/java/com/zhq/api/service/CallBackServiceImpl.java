package com.zhq.api.service;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.zhq.api.config.AlipayConfig;
import com.zhq.common.base.BaseApiService;
import com.zhq.common.base.ResponseBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: zhq_shopp_parent
 * @description: 支付宝支付回调
 * @author: HQ Zheng
 * @create: 2019-10-22 21:18
 */
@Slf4j
@RestController
public class CallBackServiceImpl extends BaseApiService implements CallBackService {

    /**
     * 支付宝同步支付
     * @param params 支付宝参数
     * @return ResponseBase
     */
    @Override
    public ResponseBase synCallBack(Map<String, String> params){
        //1、日志记录
        log.info("#####支付宝同步通知synCallBack()开始，params:{}", params);
        try {
            //2、调用SDK验证签名
            boolean signVerified =
                    AlipaySignature.rsaCheckV1(
                            params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
            log.info("#####调用支付宝SDK验证签名，signVerified:{}", signVerified);
            //——请在这里编写您的程序（以下代码仅作参考）——
            if (!signVerified) {
                return setResultError("验证签名失败");
            }//商户订单号

            String outTradeNo = params.get("out_trade_no");
            //支付宝交易号
            String tradeNo = params.get("trade_no");
            //付款金额
            String totalAmount = params.get("total_amount");
            JSONObject data = new JSONObject();
            data.put("outTradeNo",outTradeNo);
            data.put("tradeNo",tradeNo);
            data.put("totalAmount",totalAmount);
            return setResultSuccess(data);
        } catch (AlipayApiException e) {
            log.info("#####支付宝同步通知synCallBack()异常，ERROR:{}", e);
            return setResultError("系统错误！");
        }finally {
            log.info("#####支付宝同步通知synCallBack()结束，params:{}", params);
        }
    }

    @Override
    public String asynCallBack(Map<String, String> params) {
        return null;
    }
}
