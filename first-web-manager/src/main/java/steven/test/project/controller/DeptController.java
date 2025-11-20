package steven.test.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import steven.test.project.convert.DeptConvert;
import steven.test.project.service.DeptService;
import steven.test.project.vo.DeptCreateReqVO;
import steven.test.project.vo.DeptRespVO;
import steven.test.project.vo.DeptUpdateReqVO;
import steven.test.project.zhao.Dept;
import steven.test.project.zhao.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

// ==== 日志用法 ====
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// private static final Logger log = LoggerFactory.getLogger(DeptController.class);
// log.info("查询部门列表 pageNum={}, pageSize={}, name={}", pageNum, pageSize, name);

//============== 控制层类 用于最先接受到前端请求 三层中的第一层 增删改查 swagger配置  ===========
@Tag(name = "部门管理", description = "部门的增删改查接口")
@RestController
public class DeptController {
    private final DeptService deptService;

    // 构造器注入
    @Autowired
    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    // 获取列表分页
    @GetMapping("/depts/page")
    @Operation(summary = "分页查询部门列表")
    public Result list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String name
    ) {
        List<Dept> list = deptService.getDeptList(pageNum, pageSize, name);
        List<DeptRespVO> voList = DeptConvert.INSTANCE.convertList(list);
        Long total = deptService.countDepts(name);

        return Result.successPage(voList, total);
    }

    // 新增部门接口（POST）
    @PostMapping("/depts/add")
    @Operation(summary = "新增部门")
    public Result add(@RequestBody DeptCreateReqVO reqVO) {
        Dept dept = DeptConvert.INSTANCE.convert(reqVO);

        deptService.insertDept(dept);
        return Result.success("新增成功");
    }

    // 修改（更新）部门信息
    @PutMapping("/depts/update")
    @Operation(summary = "更新部门信息")
    public Result update(@RequestBody DeptUpdateReqVO reqVO) {
        Dept dept = DeptConvert.INSTANCE.convert(reqVO);

        deptService.updateDept(dept);
        return Result.success("更新成功");
    }

    // 删除
    @Operation(summary = "删除部门")
    @DeleteMapping("/depts/delete")
    public Result deptsDelete(@RequestParam("id") Long id) {
        deptService.deleteById(id);
        // System.out.println(data);
        return Result.success();
    }
}
