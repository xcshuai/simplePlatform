package com.revert.platform.common.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * controller 方法 日志记录
 */
@Log4j2
@Aspect /** 声明此类是 切面*/
@Component
public class WebLogAspect {

    //ThreadLocal保证不受其他线程影响，用于记录接口响应时间
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();

    @Pointcut("execution(public * com.revert..*.controller..*.*(..))")
    public void pointcut(){}

    @Before(value = "pointcut()")
    public void doBefore(JoinPoint joinPoint){
        MethodSignature methodSignature = ((MethodSignature)joinPoint.getSignature());
        String doMethod = methodSignature.getDeclaringType().toString()+"."+methodSignature.getMethod().getName();
        String paramsNames[] = methodSignature.getParameterNames();
        Object paramsValues[] = joinPoint.getArgs();
        log.info("方法:{},参数名称:{},参数值:{}",doMethod,paramsNames,paramsValues);
        threadLocal.set(System.currentTimeMillis());
    }

    @AfterReturning(value = "pointcut()",returning = "res")
    public void doAfter(JoinPoint joinPoint, Object res){
        MethodSignature methodSignature = ((MethodSignature)joinPoint.getSignature());
        String doMethod = methodSignature.getDeclaringType().toString()+"."+methodSignature.getMethod().getName();
        /**记录一下接口响应时间*/
        Long execTime = System.currentTimeMillis() - threadLocal.get();
        log.info("方法:{},执行时间(毫秒):{},返回结果:{}",doMethod,execTime, JSONObject.toJSONString(res));
    }

}
