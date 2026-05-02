package steven.test.project.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import steven.test.project.mapper.TreeDefMapper;
import steven.test.project.mapper.TreeNodeMapper;
import steven.test.project.service.TreeNodeService;
import steven.test.project.vo.TreeDefRespVO;
import steven.test.project.vo.TreeDetailRespVO;
import steven.test.project.vo.TreeNodeRespVO;
import steven.test.project.zhao.ServiceException;
import steven.test.project.zhao.TreeDef;
import steven.test.project.zhao.TreeNode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class TreeNodeServiceImp implements TreeNodeService {

    private final TreeDefMapper treeDefMapper;
    private final TreeNodeMapper treeNodeMapper;

    @Autowired
    public TreeNodeServiceImp(TreeDefMapper treeDefMapper, TreeNodeMapper treeNodeMapper) {
        this.treeDefMapper = treeDefMapper;
        this.treeNodeMapper = treeNodeMapper;
    }

    @Override
    public TreeDetailRespVO getTreeByCode(String treeCode, boolean includeDisabled) {
        if (!StringUtils.hasText(treeCode)) {
            throw new ServiceException(400, "树编码 code 不能为空");
        }
        TreeDef def = treeDefMapper.selectOne(
                new LambdaQueryWrapper<TreeDef>().eq(TreeDef::getCode, treeCode.trim())
        );
        if (def == null) {
            throw new ServiceException(404, "未找到树定义：" + treeCode);
        }

        LambdaQueryWrapper<TreeNode> nodeWrapper = new LambdaQueryWrapper<TreeNode>()
                .eq(TreeNode::getTreeId, def.getId())
                .orderByAsc(TreeNode::getSort)
                .orderByAsc(TreeNode::getId);
        if (!includeDisabled) {
            nodeWrapper.eq(TreeNode::getStatus, 1);
        }
        List<TreeNode> flat = treeNodeMapper.selectList(nodeWrapper);

        Map<Long, TreeNodeRespVO> idToVo = new LinkedHashMap<>();
        for (TreeNode n : flat) {
            idToVo.put(n.getId(), toVo(n));
        }

        List<TreeNodeRespVO> roots = new ArrayList<>();
        for (TreeNode n : flat) {
            TreeNodeRespVO vo = idToVo.get(n.getId());
            Long pid = n.getParentId();
            if (pid == null) {
                roots.add(vo);
                continue;
            }
            TreeNodeRespVO parent = idToVo.get(pid);
            if (parent != null) {
                parent.getChildren().add(vo);
            } else {
                roots.add(vo);
            }
        }

        TreeDetailRespVO out = new TreeDetailRespVO();
        out.setTreeDef(toDefVo(def));
        out.setRoots(roots);
        return out;
    }

    private static TreeDefRespVO toDefVo(TreeDef def) {
        TreeDefRespVO vo = new TreeDefRespVO();
        vo.setId(def.getId());
        vo.setCode(def.getCode());
        vo.setName(def.getName());
        vo.setRemark(def.getRemark());
        vo.setCreateTime(def.getCreateTime());
        vo.setUpdateTime(def.getUpdateTime());
        return vo;
    }

    private static TreeNodeRespVO toVo(TreeNode n) {
        TreeNodeRespVO vo = new TreeNodeRespVO();
        vo.setId(n.getId());
        vo.setTreeId(n.getTreeId());
        vo.setParentId(n.getParentId());
        vo.setName(n.getName());
        vo.setCode(n.getCode());
        vo.setSort(n.getSort());
        vo.setLevel(n.getLevel());
        vo.setIsLeaf(n.getLeaf());
        vo.setPath(n.getPath());
        vo.setExtJson(n.getExtJson());
        vo.setStatus(n.getStatus());
        vo.setRemark(n.getRemark());
        vo.setCreateTime(n.getCreateTime());
        vo.setUpdateTime(n.getUpdateTime());
        vo.setChildren(new ArrayList<>());
        return vo;
    }
}
