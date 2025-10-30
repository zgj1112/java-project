package steven.test.project.zhao;

// 后端统一返回结果
public class Result {
    private Integer code;
    private String message;
    private Object data;

    // 无数据
    public static Result success() {
        return success(null);
    }

    // 有数据
    public static Result success(Object data) {
        Result result = new Result();
        result.code = 200;
        result.message = "success";
        result.data = data;
        return result;
    }

    public static Result error(Integer code, String message) {
        Result result = new Result();
        result.code = code;
        result.message = message;
        result.data = null;
        return result;
    }
}
