package steven.test.project.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "更新部门请求")
public class DeptUpdateReqVO {

    @Schema(description = "部门ID", example = "1")
    private Long id;

    @Schema(description = "部门名称", example = "研发部")
    private String name;

    @Schema(description = "人数", example = "1")
    private Integer count;

    @Schema(description = "备注", example = "哈哈哈")
    private String remark;
}
