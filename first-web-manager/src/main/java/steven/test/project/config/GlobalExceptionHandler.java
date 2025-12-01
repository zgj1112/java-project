package steven.test.project.config;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import steven.test.project.zhao.Result;
import steven.test.project.zhao.ServiceException;

/**
 * 全局异常处理器
 * 使用 @RestControllerAdvice 统一处理所有 Controller 抛出的异常
 */
// ==============  全局异常处理器  ===========
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 捕获自定义的业务异常 ServiceException
     */
    @ExceptionHandler(ServiceException.class)
    public Result handleServiceException(ServiceException e) {
        // 业务异常，只记录 WARN 级别日志，避免刷屏
        log.warn("[ServiceException] 业务异常：{}", e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 捕获所有未处理的 Exception（系统异常）
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        // 系统异常，记录 ERROR 级别日志，方便排查
        log.error("[SystemException] 系统异常：", e);
        // 返回通用的错误信息，避免敏感信息泄露
        return Result.error(500, "系统繁忙，请稍后再试");
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public Result handleNoResourceFound(NoResourceFoundException ex, HttpServletRequest req) {
        if (req.getRequestURI().equals("/favicon.ico")) {
            // 不处理，不打印日志
            return null;
        }
        log.warn("[NoResourceFoundException] 请求的资源未找到：{}", ex.getMessage());
        return Result.error(404, "资源未找到");
    }
}
