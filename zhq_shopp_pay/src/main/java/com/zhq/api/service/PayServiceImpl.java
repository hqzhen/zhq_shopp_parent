package com.zhq.api.service;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.zhq.api.config.AlipayConfig;
import com.zhq.api.dao.PaymentInfoDao;
import com.zhq.api.entity.PaymentInfo;
import com.zhq.common.base.BaseApiService;
import com.zhq.common.base.ResponseBase;
import com.zhq.common.constants.Constants;
import com.zhq.common.redis.BaseRedisService;
import com.zhq.common.utils.TokenUtils;
import org.apache.commons.lang.StringUtils;
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
public class PayServiceImpl extends BaseApiService implements PayService {
    @Autowired
    private PaymentInfoDao paymentInfoDao;

    @Autowired
    private BaseRedisService baseRedisService;

    /**
     * 创建支付令牌
     *
     * @param paymentInfo 订单信息
     * @return ResponseBase
     */
    @Override
    public ResponseBase createToken(@RequestBody PaymentInfo paymentInfo) {
        //1、创建支付请求信息
        Integer payId = paymentInfoDao.savePaymentType(paymentInfo);
        //2、生成对应的token
        if (payId <= 0) {
            return setResultError("创建支付订单失败！");
        }
        //3、生成对应的token
        String payToken = TokenUtils.getPayToken();
        //3、存放到redis中
        baseRedisService.setString(payToken, paymentInfo.getId(), Constants.PAY_TOKEN_TIMEOUT);
        //4、返回token
        JSONObject data = new JSONObject();
        data.put("payToken", payToken);
        return setResultSuccess(data);
    }

    /**
     * 使用令牌查找支付信息
     *
     * @param payToken 支付令牌
     * @return ResponseBase
     */
    @Override
    public ResponseBase findPayToken(@RequestParam String payToken) {
        //1、验证参数
        if (StringUtils.isBlank(payToken)) {
            return setResultError("token不能为空");
        }
        //2、判断token有效期
        String payIdStr = (String) baseRedisService.getString(payToken);
        if (StringUtils.isBlank(payIdStr)) {
            return setResultError("支付信息超时!");
        }
        //3、使用支付id下单
        Long payId = Long.valueOf(payIdStr);
        PaymentInfo paymentInfo = paymentInfoDao.getPaymentInfo(payId);
        if(paymentInfo==null){
            return setResultError("未找到支付信息!");
        }
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = paymentInfo.getOrderId();
        //付款金额，必填
        String total_amount = paymentInfo.getPrice()+"";
        //订单名称，必填
        String subject = "充值会员";
        //商品描述，可空
        //String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                //+ "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        try {
            String result = alipayClient.pageExecute(alipayRequest).getBody();
            JSONObject data = new JSONObject();
            data.put("payHtml", result);
            return setResultSuccess(data);
        } catch (AlipayApiException e) {
            return setResultError("支付接口异常!");
        }

    }
}
