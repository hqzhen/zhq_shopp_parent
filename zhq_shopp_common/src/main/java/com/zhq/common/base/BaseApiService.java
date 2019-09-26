package com.zhq.common.base;

import com.zhq.common.constants.BaseApiServiceConstants;
import com.zhq.common.constants.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zhq_shopp_parent
 * @description: 基础api服务类
 * @author: HQ Zheng
 * @create: 2019-09-14 21:53
 */
public class BaseApiService {

    public ResponseBase setResultSuccess() {
        return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, null);
    }
    // 返回成功 ,data可传
    public ResponseBase setResultSuccess(Object data) {
        return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, data);
    }
    // 返回失败
    public ResponseBase setResultError(String msg){
        return setResult(Constants.HTTP_RES_CODE_500,msg, null);
    }
    // 自定义返回结果
    public ResponseBase setResult(Integer code, String msg, Object data) {
        ResponseBase responseBase = new ResponseBase();
        responseBase.setCode(code);
        responseBase.setMsg(msg);
        if (data != null)
            responseBase.setData(data);
        return responseBase;
    }

}
