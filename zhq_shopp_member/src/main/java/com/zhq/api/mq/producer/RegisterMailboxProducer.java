package com.zhq.api.mq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @program: zhq_shopp_parent
 * @description: 会员注册邮件推送生产者
 * @author: HQ Zheng
 * @create: 2019-09-15 20:10
 */
@Service("registerMailboxProducer")
public class RegisterMailboxProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMess(Destination destination, String json) {
        jmsMessagingTemplate.convertAndSend(destination, json);
    }


}

