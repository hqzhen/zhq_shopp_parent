package com.zhq.api.service;

import com.zhq.api.entity.UserEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @program: zhq_shopp_parent
 * @description: 用户服务
 * @author: HQ Zheng
 * @create: 2019-09-15 13:21
 */
@RequestMapping("/member")
public interface UserService {

    /**
     * 用户注册服务
     * @param userEntity
     * @return
     */
    @PostMapping("/register")
    public Map<String,Object> register(UserEntity userEntity);

}
