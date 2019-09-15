package com.zhq.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @program: zhq_shopp_parent
 * @description: 基础实体类 封装相同的字段属性
 * @author: HQ Zheng
 * @create: 2019-09-15 11:43
 */
@Getter
@Setter
public class BaseEntity {
    private Long id;//主键
    private Timestamp created;//创建时间
    private Timestamp updated;//修改时间
}
