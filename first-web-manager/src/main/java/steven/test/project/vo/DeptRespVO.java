package steven.test.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "部门返回对象")
public class DeptRespVO {

    @Schema(description = "部门名称", example = "研发部")
    private String name;

    @Schema(description = "人数", example = "1")
    private Integer count;

    @Schema(description = "备注", example = "哈哈哈")
    private String remark;

    @Schema(description = "创建时间", example = "2023-01-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", example = "2023-01-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}