package com.zhq.common.utils;

import com.zhq.common.constants.Constants;

import java.util.UUID;

/**
 * @program: zhq_shopp_parent
 * @description: token生成
 * @author: HQ Zheng
 * @create: 2019-09-27 10:15
 */
public class TokenUtils {
    /**
     * 随机生成会员token
     * @return String
     */
    public static String getUserToken(){
        return Constants.MEMBER_TOKEN+"-"+UUID.randomUUID();
    }
    public static String getPayToken(){
        return Constants.MEMBER_PAY+"-"+UUID.randomUUID();
    }

}
