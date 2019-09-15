package com.zhq.distribute;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhq.adapter.MessageAdapter;
import com.zhq.common.constants.MQInterfaceType;
import com.zhq.service.SMSMailboxService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @program: zhq_shopp_parent
 * @description: 消息消费类 从MQ中
 * @author: HQ Zheng
 * @create: 2019-09-15 21:32
 */
@Slf4j
@Component
public class ConsumerDistribute {

    @Autowired
    private SMSMailboxService smsMailboxService;

    /**
     * @methodDesc: 功能描述:(接受生产者消息)
     * @param: 报文json
     */
    @JmsListener(destination = "mail_queue")
    public void distribute(String json) {
        if (StringUtils.isBlank(json)) {
            return;
        }
        log.info("###接受到消息内容json:{}", json);
        JSONObject root = JSON.parseObject(json);
        JSONObject header = root.getJSONObject("header");
        String interfaceType = header.getString("interfaceType");
        if (StringUtils.isBlank(interfaceType)) {
            return;
        }
        MessageAdapter messageAdapter = null;
        switch (interfaceType) {
            // 发送邮箱
            case MQInterfaceType.SMS_MAIL:
                messageAdapter = smsMailboxService;
                break;
            default:
                break;
        }
        if(messageAdapter!=null){
            // 执行消息服务
            JSONObject content = root.getJSONObject("content");
            messageAdapter.distribute(content);
        }

    }

}
