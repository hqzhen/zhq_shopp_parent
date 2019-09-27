package com.zhq.api.manage.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhq.api.dao.UserDao;
import com.zhq.api.entity.UserEntity;
import com.zhq.api.manage.UserServiceManage;
import com.zhq.api.mq.producer.RegisterMailboxProducer;
import com.zhq.common.constants.DBTableName;
import com.zhq.common.constants.MQInterfaceType;
import com.zhq.common.utils.DateUtils;
import com.zhq.common.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @program: zhq_shopp_parent
 * @description: 用户服务管理实现类
 * @author: HQ Zheng
 * @create: 2019-09-15 13:33
 */
@Slf4j
@Service
public class UserServiceManageImpl implements UserServiceManage {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RegisterMailboxProducer registerMailboxProducer;

    @Value("${messages.queue}")
    private String MESSAGES_QUEUE;


    /**
     * 注册一个会员
     * @param userEntity
     */
    @Override
    public void register(UserEntity userEntity) {
        userEntity.setCreated(DateUtils.getTimestamp());
        userEntity.setUpdated(DateUtils.getTimestamp());
        userEntity.setPassword(md5PassSalt(userEntity.getPhone(),userEntity.getPassword()));
        userDao.save(userEntity,DBTableName.MEMBER_MB_USER);
        String mailMessage = mailMessage(userEntity.getEmail(), userEntity.getUserName());
        log.info("###register() 注册发送邮件报文mailMessage:{}",mailMessage);
        //发送注册邮件
        sendMailMessage(mailMessage);
    }

    /**
     * 密码加盐
     * @param phone
     * @param password
     * @return
     */
    @Override
    public String md5PassSalt(String phone, String password) {
        return MD5Util.MD5(password);
    }

    /**
     * 组装邮件发送Json格式报文
     * @param mail
     * @return
     */
    private String mailMessage(String mail,String userName) {
        JSONObject root = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("interfaceType", MQInterfaceType.SMS_MAIL);
        JSONObject content = new JSONObject();
        content.put("mail", mail);
        content.put("userName", userName);
        root.put("header", header);
        root.put("content", content);
        return root.toJSONString();
    }

    /**
     * 推送注册消息到MQ
     * @param json
     */
    private void sendMailMessage(String json) {
        Destination activeMQQueue = new ActiveMQQueue(MESSAGES_QUEUE);
        registerMailboxProducer.sendMess(activeMQQueue, json);
    }


}
