package com.yjc.find.base.bean;

import javax.validation.constraints.NotNull;

public class ResultMsgFactory {
    /**
     * 创建一个成功消息
     * @return
     */
    public static ResultMsg createSuccessMsg(){

        return createSuccessMsg("");
    }

    /**
     * 创建一个成功消息
     * @param data
     * @return
     */
    public static ResultMsg createSuccessMsg(Object data){

        return createSuccessMsg("成功",data);
    }

    /**
     * 创建一个成功消息
     * @param msg
     * @param data
     * @return
     */
    public static ResultMsg createSuccessMsg(String msg,Object data){

        return createMsg(0, msg, data);
    }

    /**
     * 创建一个错误消息
     * @return
     */
    public static ResultMsg createErrorMsg(){

        return createErrorMsg("");
    }

    /**
     * 创建一个错误消息
     * @param msg
     * @return
     */
    public static ResultMsg createErrorMsg(String msg){

        return createErrorMsg(msg, "");
    }

    /**
     * 创建一个错误消息
     * @param msg
     * @param data
     * @return
     */
    public static ResultMsg createErrorMsg(String msg,Object data){

        return createMsg(1, msg, data);
    }

    public static ResultMsg createMsg(@NotNull Integer code, String msg, Object data){

        ResultMsg resultMsg=new ResultMsg();
        resultMsg.setCode(code);
        if(msg==null)msg="";
        resultMsg.setMsg(msg);
        if(data==null)data="";
        resultMsg.setData(data);
        return resultMsg;
    }

}
