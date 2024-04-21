package com.dakkk.dkblog.common.aspect;

import com.dakkk.dkblog.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ClassName: APIOperationLogAspect
 * Package: com.dakkk.dkblog.common.aspect
 *
 * @Create 2024/4/21 19:52
 * @Author dakkk
 * Description:
 */
@Aspect
@Component
@Slf4j
public class ApiOperationLogAspect {
    /**
     * 自定义 @ApiOperationLog 注解为切入点，凡是使用该注解的方法
     * 都会被设置环绕通知，可以实现其他四种通知类型的作用
     */
    @Pointcut("@annotation(com.dakkk.dkblog.common.aspect.ApiOperationLog)")
    public void apiOperationLog(){}

    @Around("apiOperationLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        try {
            // 请求开始时间
            Long startTime = System.currentTimeMillis();

            // MDC
            MDC.put("traceId", UUID.randomUUID().toString());

            // 获取被请求的类和方法
            String className = pjp.getTarget().getClass().getSimpleName();
            String methodName = pjp.getSignature().getName();

            // 获取请求入参
            Object[] args = pjp.getArgs();
            // 请求入参转化为 JSON 字符串
            // 将数组args转换为流，然后使用map方法，将每个元素转为JSON字符串
            // 最后，使用collect，将每个字符串使用 逗号 分隔
            String argsJsonStr = Arrays.stream(args).map(toJsonStr()).collect(Collectors.joining(","));

            // 功能描述信息
            String desc = getApiOperationLogDesc(pjp);

            // 打印请求的相关信息
            log.info("===Start=== 请求开始：【{}】，入参：【{}】，请求类：【{}】，请求方法：【{}】===END===",desc,argsJsonStr,className,methodName);

            // 执行切入点方法，获取出参对象（返回值）
            Object result = pjp.proceed();

            // 执行耗时
            long executionTime = System.currentTimeMillis() - startTime;

            // 打印出参的相关信息
            log.info("===Start=== 请求结束：【{}】，耗时：【{}ms】，出参：【{}】===END===",desc,executionTime,result);

            // 返回方法原本的返回值
            return result;
        }finally {
            MDC.clear();
        }
    }

    /**
     * 获取切入点方法上@ApiOperationLog注解的description属性
     * @param joinPoint 切入点
     * @return @ApiOperationLog注解的description属性
     */
    private String getApiOperationLogDesc(ProceedingJoinPoint pjp){
        // 从 joinPoint 获取 方法签名
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();

        // 使用方法签名 获取当前被注解的 方法
        Method method = methodSignature.getMethod();

        // 从 方法 中提出去 ApiOperationLog 注解
        ApiOperationLog annotation = method.getAnnotation(ApiOperationLog.class);

        // 从注解中 获取 desc
        return annotation.description();
    }

    /**
     * 返回一个function，x=obj，y=string ： y = f(x)
     * @return 返回obj的json字符串
     */
    private Function<Object,String> toJsonStr(){
        return JsonUtil::toJsonString;
        // return arg -> JsonUtil.toJsonString(arg);
    }

    // private Function<Object,String> toJsonStr(){
    //     return new Function<Object, String>() {
    //         @Override
    //         public String apply(Object arg) {
    //             return JsonUtil.toJsonString(arg);
    //         }
    //     };
    // }
}
