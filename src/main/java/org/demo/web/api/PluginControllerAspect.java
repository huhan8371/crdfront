package org.demo.web.api;

/**
 * Created by hanhu on 16/7/1.
 */

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class PluginControllerAspect {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(public * org.demo.web.api.PluginController.*(..))")
    public void logBeforeRestCall(JoinPoint pjp) throws Throwable {
        logger.debug(":::::AOP Before PluginController call:::::" + pjp);

    }


    @AfterThrowing(value = "execution(public * org.demo.web.api.PluginController.*(..))", throwing = "e")
    public void doThrow(JoinPoint jp, Throwable e) {
        System.out.println("删除出错啦");
    }
//
//    @Around("execution(public * PluginController.*(..))")
//    public void aroundCall(ProceedingJoinPoint point) throws Throwable {
//        System.out.println(":::::AOP Around PluginController call:::::" + point);
//
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
//                .getRequestAttributes()).getRequest();
//        System.err.println(request.getRequestURI());
//    }
}
