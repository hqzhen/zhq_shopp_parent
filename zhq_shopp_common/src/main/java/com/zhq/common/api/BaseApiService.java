package com.zhq.common.api;

import com.zhq.common.constants.BaseApiServiceConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zhq_shopp_parent
 * @description: 基础api服务类
 * @author: HQ Zheng
 * @create: 2019-09-14 21:53
 */
public class BaseApiService {

    /**
     * 自定义返回
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public Map<String,Object> setResult(Integer code,String msg,Object data){
        Map<String,Object> result=new HashMap<>();
        result.put(BaseApiServiceConstants.HTTP_RES_CODE_NAME,code);
        result.put(BaseApiServiceConstants.HTTP_RES_CODE_MSG,msg);
        if(data!=null)
        result.put(BaseApiServiceConstants.HTTP_RES_CODE_DATA,data);
        return result;
    }

    /**
     * 返回成功
     * @param msg
     * @return
     */
    public Map<String,Object> setResultSuccess(String msg){

        return setResult(BaseApiServiceConstants.HTTP_RES_CODE_200,msg,null);
    }

    /**
     * 返回成功
     * @param data
     * @return
     */
    public Map<String,Object> setResultSuccess(Object data){

        return setResult(BaseApiServiceConstants.HTTP_RES_CODE_200,BaseApiServiceConstants.HTTP_RES_CODE_200_VALUE,data);
    }

    /**
     * 返回成功
     * @return
     */
    public Map<String,Object> setResultSuccess(){

        return setResult(BaseApiServiceConstants.HTTP_RES_CODE_200,BaseApiServiceConstants.HTTP_RES_CODE_200_VALUE,null);
    }

    /**
     * 返回错误
     * @param msg
     * @return
     */
    public Map<String,Object> setResultFail(String msg){

        return setResult(BaseApiServiceConstants.HTTP_RES_CODE_500,msg,null);
    }

    /**
     * 返回错误
     * @return
     */
    public Map<String,Object> setResultFail(){

        return setResult(BaseApiServiceConstants.HTTP_RES_CODE_500,BaseApiServiceConstants.HTTP_RES_CODE_500_VALUE,null);
    }



}
