package com.zhq.api.service.impl;

import com.zhq.api.dao.UserDao;
import com.zhq.api.entity.UserEntity;
import com.zhq.api.manage.UserServiceManage;
import com.zhq.api.service.UserService;
import com.zhq.common.base.BaseApiService;
import com.zhq.common.base.ResponseBase;
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

    @Autowired
    private UserDao userDao;

    @Override
    public ResponseBase register(@RequestBody UserEntity userEntity) {
        if(StringUtils.isBlank(userEntity.getUserName())){
            return setResultError("用户名不能为空！");
        }
        try {
            userServiceManage.register(userEntity);
        } catch (Exception e) {
            log.error("###register() ERROR:",e);
            return setResultError("注册失败！请联系管理员");
        }
        return setResultSuccess();
    }

    /**
     * 测试接口
     * @return
     */
    @Override
    public ResponseBase testResponseBase() {
        return setResultSuccess();
    }

    /**
     * 查找会员
     * @param userId 会员id
     * @return ResponseBase
     */
    @Override
    public ResponseBase findUser(Long userId) {
        UserEntity user = userDao.findByID(userId);
        if(user==null){
            return setResultError("未查找到会员信息。");
        }
        return setResultSuccess(user);
    }
}
