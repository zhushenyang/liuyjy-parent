package com.example.apiadmin.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class WebLogAspect {

    /*long fromdate = 0l;
    long todate = 0l;

    //@Pointcut("execution(public * com.mkeeper.controller.logging..*.*(..))")
    @Pointcut("execution(public * com.example.admin.controller..*.*(..))")
    public void webLog() {
        log.info("——————进入——————");
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        fromdate = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("<<<<<<<<<<<<<开始处理：《" + new Date() + "》<<<<<<<<<<<");
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        todate = System.currentTimeMillis();
        // 处理完请求，返回内容

        log.info("RESPONSE : " + JSONObject.toJSON(ret));
        log.info(">>>>>>>>>>>结束处理：《" + new Date() + "》>>:花费时间：《《" + (todate - fromdate) + "》》>>>>>>>>>>>>>>");
    }*/
}

