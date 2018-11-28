package com.revert.platform.common.base.controller;

import com.revert.platform.common.base.model.WebResult;
import com.revert.platform.common.constant.ErrorInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class BaseControllerExceptionHandler {


    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public WebResult handleException(Exception e){
        log.error(e);
        WebResult result = new WebResult()
                .code(ErrorInfo.CODE_ERROR)
                .message(ErrorInfo.MSG_ERROR);
        return result;
    }



}
