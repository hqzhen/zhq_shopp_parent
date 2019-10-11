package com.zhq.weixin.controller;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: zhq_shopp_parent
 * @description: 测试demo
 * @author: HQ Zheng
 * @create: 2019-10-11 10:48
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private WxMpService wxMpService;

    /**
     * 测试发送消息模板
     *
     * @param wxMpTemplateMessage 消息模板信息
     * @return String
     * @throws WxErrorException 异常
     * 参数案例：
     * {
     * "toUser": "olofrvsSu1JcyH5sSQ0xTWkI044M",
     * "templateId": "vLYABjJI7ngueZDYs5h3jxwIxXu9UvAG7eRHo51jxGM",
     * "url": "https://www.baidu.com",
     * "data": [
     * {
     * "name": "first",
     * "value": "2019-10-11",
     * "color": "#173177"
     * },
     * {
     * "name": "keyword1",
     * "value": "128.15",
     * "color": "#173177"
     * },
     * {
     * "name": "keyword2",
     * "value": "A1002",
     * "color": "#173177"
     * }
     * ]
     * }
     *
     */
    @RequestMapping("/sendTemplate")
    public String sendTemplate(@RequestBody WxMpTemplateMessage wxMpTemplateMessage) throws WxErrorException {
        WxMpTemplateMsgService templateMsgService = wxMpService.getTemplateMsgService();

        return templateMsgService.sendTemplateMsg(wxMpTemplateMessage);
    }

    /**
     * 测试构建授权链接
     *
     * @param url 要授权的url
     * @return String
     * 参数样例：
     * http://localhost:8765/test/getMPOAuth2Url?url=http%3a%2f%2ffd4d774e.ngrok.io%2ftest%2fgetUserInfo
     */
    @RequestMapping("/getMPOAuth2Url")
    public String getMPOAuth2Url(String url) {
        return wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
    }

    /**
     * 测试通过授权链接获取用户信息
     * @param code 授权code
     * @return String
     * @throws WxErrorException 异常
     * 返回样例
     * {
     * "openId": "olofrvsSu1JcyH5sSQ0xTWkI044M",
     * "nickname": "夏木炎๑㉨๑",
     * "sexDesc": "男",
     * "sex": 1,
     * "language": "zh_CN",
     * "city": "",
     * "province": "",
     * "country": "百慕大",
     * "headImgUrl": "http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKu1fFjt7E3IOATxPIhqg8DrsicxQRnHicw2xFJxs2d1ZV3E1qgZ6B9gYwNP9Pt1ibpL3ahbZhJ8ARnw/132",
     * "privileges": []
     * }
     */
    @RequestMapping("/getUserInfo")
    public String getUserInfo(String code) throws WxErrorException {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
        wxMpOAuth2AccessToken = wxMpService.oauth2refreshAccessToken(wxMpOAuth2AccessToken.getRefreshToken());
        boolean valid = wxMpService.oauth2validateAccessToken(wxMpOAuth2AccessToken);
        System.out.println("valid:"+valid);
        return  wxMpUser.toString();
    }


}

