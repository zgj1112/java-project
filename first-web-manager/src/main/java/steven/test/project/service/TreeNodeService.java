package steven.test.project.service;

import steven.test.project.vo.TreeDetailRespVO;

public interface TreeNodeService {

    /**
     * 按树编码查询整棵树（嵌套 children），用于前端树组件。
     *
     * @param treeCode        tree_def.code，如 MENU、CATEGORY
     * @param includeDisabled 为 true 时包含 status=0 的节点
     */
    TreeDetailRespVO getTreeByCode(String treeCode, boolean includeDisabled);
}
