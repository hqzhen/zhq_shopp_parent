package com.zhq.common.constants;

public interface Constants {
    // 响应code
    String HTTP_RES_CODE_NAME = "code";
    // 响应msg
    String HTTP_RES_CODE_MSG = "msg";
    // 响应data
    String HTTP_RES_CODE_DATA = "data";
    // 响应请求成功
    String HTTP_RES_CODE_200_VALUE = "success";
    // 系统错误
    String HTTP_RES_CODE_500_VALUE = "fial";
    // 响应请求成功code
    Integer HTTP_RES_CODE_200 = 200;
    // 系统错误
    Integer HTTP_RES_CODE_500 = 500;

    // 未关联QQ
    Integer HTTP_RES_CODE_201 = 201;
    //会员token
    String MEMBER_TOKEN="MEMBER_TOKEN";
    //支付token
    String MEMBER_PAY="MEMBER_PAY";
    //redis token登入有效期
    Long MEMBER_TOKEN_TIMEOUT=(long) 60*60*24*90;
    //浏览器 cookie token登入有效期
    Integer COOKIE_TOKEN_TIMEOUT=60*60*24*89;
    // cookie 会员 totoken 名称
    String COOKIE_MEMBER_TOKEN ="cookie_member_token";

}
