package steven.test.project.vo;

import lombok.Data;

import java.util.List;

/**
 * 一棵树：元信息 + 根节点列表（子节点在 children 中递归）。
 */
@Data
public class TreeDetailRespVO {
    private TreeDefRespVO treeDef;
    private List<TreeNodeRespVO> roots;
}
