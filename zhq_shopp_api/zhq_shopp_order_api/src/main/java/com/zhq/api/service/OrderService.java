package com.zhq.api.service;

import com.zhq.common.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/order")
public interface OrderService {
    /**
     * 更新订单
     * @param isPay 支付状态
     * @param aliPayId 支付号
     * @param orderNumber 订单号
     * @return ResponseBase
     */
    ResponseBase updateOrder(@RequestParam("isPay") Long isPay,
                             @RequestParam("payId") String aliPayId,
                             @RequestParam("orderNumber") String orderNumber);

}
