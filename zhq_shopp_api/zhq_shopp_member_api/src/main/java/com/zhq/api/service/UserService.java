package com.zhq.api.service;

import com.zhq.api.entity.UserEntity;
import com.zhq.common.base.ResponseBase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * @param userEntity 用户实体
     * @return
     */
    @PostMapping("/register")
    ResponseBase register(@RequestBody UserEntity userEntity);

    @RequestMapping("/testResponseBase")
    ResponseBase testResponseBase();

    /**
     * 查找用户信息
     * @param userId 用户id
     * @return ResponseBase
     */
    @PostMapping("/findUser")
    ResponseBase findUser(Long userId);

    /**
     * 用户登入
     * @param userEntity 用户信息
     * @return  ResponseBase
     */
    @RequestMapping("/login")
    ResponseBase login(@RequestBody UserEntity userEntity);

    /**
     * 使用token进行登入
     * @return ResponseBase
     */
    @RequestMapping("/findByUserToken")
    ResponseBase findByUserToken(String token);



}
