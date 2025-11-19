package steven.test.project.common.aop;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
// import org.springframework.web.bind.annotation.*;

        import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 切点（拦截所有 controller 的公共请求方法）
     */
    @Pointcut("execution(public * steven.test.project.controller..*(..))")
    public void webLog() {}

    /**
     * 请求前打印 入参日志
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();

        log.info("==> 请求开始: {}", methodName);

        Object[] args = joinPoint.getArgs();
        try {
            log.info("==> 请求参数: {}", objectMapper.writeValueAsString(args));
        } catch (Exception e) {
            log.info("==> 请求参数: {}", Arrays.toString(args));
        }
    }

    /**
     * 统一处理返回值 + 耗时
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();  // 执行 Controller 方法

        long timeCost = System.currentTimeMillis() - startTime;

        try {
            log.info("<== 返回结果: {}", objectMapper.writeValueAsString(result));
        } catch (Exception e) {
            log.info("<== 正常结果: {}", result);
        }

        log.info("<== 请求结束, 耗时: {} ms", timeCost);

        return result;
    }
}
