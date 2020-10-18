package com.anvna.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.omg.PortableServer.THREAD_POLICY_ID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.ObjectInputStream;
import java.util.Arrays;

/**
 * @ClassName LogAspect
 * @Description
 * @Author an_vna
 * @Date 2020/10/18 21:49
 * @Version V1.0
 **/
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 切面注解在何处切入
     */
    @Pointcut("execution(* com.anvna.blog.web.*.*(..))")
    public void log(){

    }

    /**
     * 在 log() 方法切面之前执行
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 获取请求 url
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr().toString();
        // 通过切片获得方法名和参数
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." +joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLogBean requestLogBean = new RequestLogBean(url, ip, classMethod, args);

        logger.info("Request: {}", requestLogBean.toString());
    }


    @After("log()")
    public void doAfter(){
        logger.info("--------------doAfter--------------");
    }

    @AfterReturning(returning = "res", pointcut = "log()")
    public void doAfterReturn(Object res){
        logger.info("Result: {}", res);
    }

    private class RequestLogBean{
        private String url;
        private String ip;
        private String className;
        private Object[] args;

        public RequestLogBean(String url, String ip, String className, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.className = className;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLogBean{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", className='" + className + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
