package steven.test.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 树节点（含 children），便于前端 Tree、Cascader 等组件直接使用。
 */
@Data
public class TreeNodeRespVO {
    private Long id;
    private Long treeId;
    private Long parentId;
    private String name;
    private String code;
    private Integer sort;
    private Integer level;
    private Boolean isLeaf;
    private String path;
    /** 原始 JSON 字符串，前端可自行 parse */
    private String extJson;
    private Integer status;
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private List<TreeNodeRespVO> children = new ArrayList<>();
}
