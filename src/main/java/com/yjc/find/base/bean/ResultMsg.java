package com.yjc.find.base.bean;

import lombok.Data;

@Data
public class ResultMsg {

    ResultMsg(){

    }

    private Integer code;

    private String msg;

    private Integer count;

    private Object data;
}
