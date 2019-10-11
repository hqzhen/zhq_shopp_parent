package com.zhq.weixin.controller;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
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
     * @param wxMpTemplateMessage 消息模板信息
     * @return String
     * @throws WxErrorException 异常
     * 参数案例
     * {
     *     "toUser": "olofrvsSu1JcyH5sSQ0xTWkI044M",
     *     "templateId": "vLYABjJI7ngueZDYs5h3jxwIxXu9UvAG7eRHo51jxGM",
     *     "url": "https://www.baidu.com",
     *     "data": [
     *         {
     *             "name": "first",
     *             "value": "2019-10-11",
     *             "color": "#173177"
     *         },
     *         {
     *             "name": "keyword1",
     *             "value": "128.15",
     *             "color": "#173177"
     *         },
     *         {
     *             "name": "keyword2",
     *             "value": "A1002",
     *             "color": "#173177"
     *         }
     *     ]
     * }
     */
    @RequestMapping("/sendTemplate")
    public String sendTemplate(@RequestBody WxMpTemplateMessage wxMpTemplateMessage) throws WxErrorException {
        WxMpTemplateMsgService templateMsgService = wxMpService.getTemplateMsgService();

        return templateMsgService.sendTemplateMsg(wxMpTemplateMessage);
    }




}

