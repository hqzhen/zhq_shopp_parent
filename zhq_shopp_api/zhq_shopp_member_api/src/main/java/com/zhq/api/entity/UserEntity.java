package com.zhq.api.entity;

import com.zhq.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @program: zhq_shopp_parent
 * @description: 会员信息
 * @author: HQ Zheng
 * @create: 2019-09-15 13:14
 */
@Getter
@Setter
public class UserEntity extends BaseEntity {
    private String userName;
    private String password;
    private String phone;
    private String email;
}
