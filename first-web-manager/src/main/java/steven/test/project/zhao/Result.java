package steven.test.project.zhao;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 后端统一返回结果
@Data
public class Result {
    private Integer code;
    private String message;
    private Object data;

    // 成功（无数据）
    public static Result success() {
        return success(null);
    }

    // 成功（带数据）
    public static Result success(Object data) {
        Result result = new Result();
        result.code = 200;
        result.message = "success";
        result.data = data;
        return result;
    }

    // 成功（带分页）
    public static Result successPage(List<?> list, Long total) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("total", total);
        return success(map);
    }

    /**
     * 失败结果（带自定义错误码和消息）
     * @param code 错误码
     * @param message 错误消息
     * @return 失败结果对象
     */
    public static Result error(Integer code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(null);
        return result;
    }

    // 失败
    // public static Result error(Integer code, String message) {
    //     Result result = new Result();
    //     result.code = code;
    //     result.message = message;
    //     result.data = null;
    //     return result;
    // }
}
