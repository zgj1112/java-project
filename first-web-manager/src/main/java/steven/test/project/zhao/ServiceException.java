package steven.test.project.zhao;

import lombok.Getter;

/**
 * 业务异常类，用于封装业务逻辑上的错误。
 * 继承自 RuntimeException，方便 Spring 事务回滚。
 */
@Getter
public class ServiceException extends RuntimeException {

    private final Integer code;

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    // public ServiceException(String message) {
    //     // 默认使用 500 作为业务错误码
    //     this(500, message);
    // }

}