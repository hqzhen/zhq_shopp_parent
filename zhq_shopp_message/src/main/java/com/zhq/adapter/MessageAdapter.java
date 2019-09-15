package com.zhq.adapter;

import com.alibaba.fastjson.JSONObject;

/**
 * 消息适配器
 */
public interface MessageAdapter {
    //接受消息
    public void distribute(JSONObject jsonObject);
}
