package com.zhq.api.manage.impl;

import com.zhq.api.dao.UserDao;
import com.zhq.api.entity.UserEntity;
import com.zhq.api.manage.UserServiceManage;
import com.zhq.common.constants.DBTableName;
import com.zhq.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: zhq_shopp_parent
 * @description: 用户服务管理实现类
 * @author: HQ Zheng
 * @create: 2019-09-15 13:33
 */
@Service
public class UserServiceManageImpl implements UserServiceManage {

    @Autowired
    private UserDao userDao;


    @Override
    public void register(UserEntity userEntity) {
        userEntity.setCreated(DateUtils.getTimestamp());
        userEntity.setUpdated(DateUtils.getTimestamp());
        userDao.save(userEntity,DBTableName.MEMBER_MB_USER);
    }
}
