package steven.test.project.zhao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tree_node")
public class TreeNode {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long treeId;
    private Long parentId;
    private String name;
    private String code;

    @TableField("`sort`")
    private Integer sort;

    private Integer level;

    @TableField("is_leaf")
    private Boolean leaf;

    private String path;

    @TableField("ext_json")
    private String extJson;

    private Integer status;
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
