package com.any.pub;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.logging.Logger;

/**
 * Created by avaio on 2017/1/9.
 */
@Aspect
public class AopLog {
    private static Logger logger = Logger.getLogger("SysAutoLog");

    @Pointcut("(execution(* com.any.service..*.*(..)))")
    public void insertServiceCall() {
    }

    @Before(value = "insertServiceCall()")
    public void logBefore(JoinPoint joinPoint) throws Throwable {
        logger.info("begin execute:" + joinPoint.getSignature().toString() + "  args:" + JSON.toJSONString(joinPoint.getArgs()));
    }

    @AfterReturning(value = "insertServiceCall()", returning = "rt")
    public void logAfter(JoinPoint joinPoint, Object rt) throws Throwable {
        logger.info("finish execute:" + joinPoint.getSignature().toString() + "  result:" + JSON.toJSONString(rt));
    }

    @AfterThrowing(value = "insertServiceCall()", throwing = "e")
    public void logException(JoinPoint joinPoint, Throwable e) throws Throwable {
        logger.info("exception execute:" + joinPoint.getSignature().toString() + "  message:" + e.getMessage());
    }
}
