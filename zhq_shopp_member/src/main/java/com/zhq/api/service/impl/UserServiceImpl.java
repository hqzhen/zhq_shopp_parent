package com.zhq.api.service.impl;

import com.zhq.api.entity.UserEntity;
import com.zhq.api.manage.UserServiceManage;
import com.zhq.api.service.UserService;
import com.zhq.common.api.BaseApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: zhq_shopp_parent
 * @description: 用户服务实现类
 * @author: HQ Zheng
 * @create: 2019-09-15 13:26
 */
@Slf4j
@RestController
public class UserServiceImpl extends BaseApiService implements UserService {
    @Autowired
    private UserServiceManage userServiceManage;
    @Override
    public Map<String, Object> register(@RequestBody UserEntity userEntity) {
        if(StringUtils.isBlank(userEntity.getUserName())){
            return setResultParameterError("用户名不能为空！");
        }
        try {
            userServiceManage.register(userEntity);
        } catch (Exception e) {
            log.error("###register() ERROR:",e);
            return setResultFail("注册失败！");
        }
        return setResultSuccess();
    }
}
