package ye.guo.huang.jwt.common.aop;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * web访问切面，统计request耗时长度
 * @author ibm
 * @since 0
 * @date 2018/8/18
 */

@Component
@Aspect
public class RequestAspect {
    private static final Logger LOGGER = LogManager.getLogger(RequestAspect.class);

    @Pointcut("execution(public * ye.guo.huang.jwt.controller..*(..)) ")
    public void restPointCut() {

    }

    @Around(value = "restPointCut()")
    public Object doAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Signature signature = joinPoint.getSignature();
        //请求的
        MethodSignature methodSignature = (MethodSignature)signature;
        //请求的控制层类-方法
        Method targetMethod = methodSignature.getMethod();
        //请求被接收的参数
        Object[] targetArgs = joinPoint.getArgs();
        //请求返回的结果
        Object restResult = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        StringBuilder restInfo = new StringBuilder(targetMethod.getDeclaringClass().getName() + ".");//请求的类名
        restInfo.append(targetMethod.getName());//请求的控制层的方法名
        restInfo.append("(" + Arrays.toString(targetArgs) + ")");//请求参数
        restInfo.append(" 耗时 ：");
        restInfo.append(duration);
        restInfo.append("毫秒");
        LOGGER.info(restInfo.toString());
        return restResult;
    }

}
