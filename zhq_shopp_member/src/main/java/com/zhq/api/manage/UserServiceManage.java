package com.zhq.api.manage;

import com.zhq.api.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @program: zhq_shopp_parent
 * @description: 用户服务管理类
 * @author: HQ Zheng
 * @create: 2019-09-15 13:31
 */
public interface UserServiceManage {
    /**
     * 注册服务
     * @param userEntity
     */
    public void register(UserEntity userEntity);

    /**
     * 密码加盐
     * @param phone
     * @param password
     * @return
     */
    public String md5PassSalt(String phone,String password);
}
