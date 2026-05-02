package steven.test.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import steven.test.project.service.TreeNodeService;
import steven.test.project.vo.TreeDetailRespVO;
import steven.test.project.zhao.Result;

@Tag(name = "通用树", description = "按树编码查询嵌套树形数据，供前端 Tree 等组件使用")
@RestController
public class TreeNodeController {

    private final TreeNodeService treeNodeService;

    @Autowired
    public TreeNodeController(TreeNodeService treeNodeService) {
        this.treeNodeService = treeNodeService;
    }

    @GetMapping("/tree-nodes/tree")
    @Operation(summary = "按树编码获取整棵树（含 children）")
    public Result tree(
            @Parameter(description = "树编码，对应 tree_def.code，如 MENU、CATEGORY", required = true)
            @RequestParam("code") String code,
            @Parameter(description = "是否包含已禁用节点（status=0），默认 false")
            @RequestParam(value = "includeDisabled", defaultValue = "false") boolean includeDisabled
    ) {
        TreeDetailRespVO detail = treeNodeService.getTreeByCode(code, includeDisabled);
        return Result.success(detail);
    }
}
