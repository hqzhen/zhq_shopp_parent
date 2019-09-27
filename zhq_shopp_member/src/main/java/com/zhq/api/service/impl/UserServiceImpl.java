package com.zhq.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhq.api.dao.UserDao;
import com.zhq.api.entity.UserEntity;
import com.zhq.api.manage.UserServiceManage;
import com.zhq.api.service.UserService;
import com.zhq.common.base.BaseApiService;
import com.zhq.common.base.ResponseBase;
import com.zhq.common.constants.Constants;
import com.zhq.common.redis.BaseRedisService;
import com.zhq.common.utils.MD5Util;
import com.zhq.common.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private BaseRedisService baseRedisService;

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

    /**
     * 会员登入
     * @param userEntity 用户信息
     * @return ResponseBase
     */
    @Override
    public ResponseBase login(@RequestBody UserEntity userEntity) {
        //1、验证参数
        String userName = userEntity.getUserName();
        String password = userEntity.getPassword();
        if(StringUtils.isBlank(userName)||StringUtils.isBlank(password)){
            return setResultError("用户名或密码为空！");
        }
        //2、数据库查找账号密码是否正确
        password=MD5Util.MD5(password);
        UserEntity user = userDao.login(userName, password);
        if(user==null){
            return setResultError("用户名或密码错误！");
        }
        //3、如果账号密码正确，生成对应的token
        String userToken = TokenUtils.getUserToken();
        //4、存放在redis中,key为token,value为userId
        log.info("###用户信息存放在redis中...key为:{},value为:{}",userToken,user.getId());
        baseRedisService.setString(userToken,user.getId().toString(),Constants.MEMBER_TOKEN_TIMEOUT);
        //5、返回token
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("userToken",userToken);
        return setResultSuccess(jsonObject);
    }

    @Override
    public ResponseBase findByUserToken(String token) {
        //1、验证参数
        if(StringUtils.isBlank(token)){
            return setResultError("token为空!");
        }
        //2、使用token查找用户id
        String userIdStr = (String) baseRedisService.getString(token);
        if(StringUtils.isBlank(userIdStr)){
            return setResultError("token无效或者已经过期!");
        }
        Long userId=Long.parseLong(userIdStr);
        //3、使用用户id查找用户信息
        UserEntity user = userDao.findByID(userId);
        if(user==null){
            return setResultError("未查找到该用户信息");
        }
        user.setPassword(null);
        return setResultSuccess(user);
    }
}
