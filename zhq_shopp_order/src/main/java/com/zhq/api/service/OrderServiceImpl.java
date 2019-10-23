package com.zhq.api.service;

import com.zhq.api.dao.OrderDao;
import com.zhq.common.base.BaseApiService;
import com.zhq.common.base.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: zhq_shopp_parent
 * @description: 订单服务
 * @author: HQ Zheng
 * @create: 2019-10-23 15:38
 */
@RestController
public class OrderServiceImpl  extends BaseApiService implements OrderService  {

    @Autowired
    private OrderDao orderDao;

    @Override
    public ResponseBase updateOrder(@RequestParam("isPay") Long isPay,
                                    @RequestParam("payId") String aliPayId,
                                    @RequestParam("orderNumber") String orderNumber) {
        int updateOrder = orderDao.updateOrder(isPay, aliPayId, orderNumber);
        if (updateOrder <= 0) {
            return setResultError("系统错误!");
        }
        return setResultSuccess();


    }
}
