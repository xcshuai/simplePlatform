package com.revert.platform.common.base.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class BaseControllerExceptionHandler {


//    /**
//     * 处理所有不可知的异常
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(Exception.class)
//    AppResponse handleException(Exception e){
//        LOGGER.error(e.getMessage(), e);
//
//        AppResponse response = new AppResponse();
//        response.setFail("操作失败！");
//        return response;
//    }



}
