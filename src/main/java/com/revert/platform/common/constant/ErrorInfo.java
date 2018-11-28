package com.revert.platform.common.constant;

import lombok.Data;

import java.io.Serializable;

public class ErrorInfo implements Serializable {

    /**
     * 异常code
     *  200:正常
     *  500:异常
     */
    public final static String CODE_SUCCESS = "200";
    public final static String CODE_ERROR = "500";




}
