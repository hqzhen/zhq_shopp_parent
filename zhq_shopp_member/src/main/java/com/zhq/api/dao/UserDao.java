package com.zhq.api.dao;

import com.zhq.api.entity.UserEntity;
import com.zhq.common.mybatis.BaseDao;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @program: zhq_shopp_parent
 * @description: userDao持久层
 * @author: HQ Zheng
 * @create: 2019-09-15 13:35
 */
@Mapper
public interface UserDao extends BaseDao {

    @Select("select  id,username,password,phone,email,created,updated from mb_user where id =#{userId}")
    UserEntity findByID(@Param("userId") Long userId);

    @Insert("INSERT  INTO `mb_user`  (username,password,phone,email,created,updated) VALUES (#{username}, #{password},#{phone},#{email},#{created},#{updated});")
    Integer insertUser(UserEntity userEntity);


}
