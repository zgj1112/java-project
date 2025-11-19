package steven.test.project.controller;

// import jakarta.servlet.http.HttpServlet;
// import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import steven.test.project.service.DeptService;
import steven.test.project.zhao.Dept;
import steven.test.project.zhao.Result;

// import java.net.http.HttpRequest;
import java.util.List;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

@RestController
public class DeptController {
    private final DeptService deptService;

    // 构造器注入
    @Autowired
    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }
    // 控制层类 用于最先接受到前端请求 三层中的第一层

    // 日志输出
    // private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    // 获取列表分页
    @GetMapping("/depts/page")
    public Result list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String name
    ) {
        // 日志
        // log.info("查询部门列表 pageNum={}, pageSize={}, name={}", pageNum, pageSize, name);

        List<Dept> list = deptService.getDeptList(pageNum, pageSize, name);
        Long total = deptService.countDepts(name);
        return Result.successPage(list, total);
    }

    // 新增部门接口（POST）
    @PostMapping("/depts/add")
    public Result add(@RequestBody Dept dept) {
        int row = deptService.insertDept(dept);
        System.out.println(row);
        return Result.success("新增成功");
    }

    // 修改（更新）部门信息
    @PutMapping("/depts/update")
    public Result update(@RequestBody Dept dept) {
        int row = deptService.updateDept(dept);
        System.out.println(row);
        return Result.success("更新成功");
        // return Result.error("更新失败");
    }

    // 删除
    @DeleteMapping("/depts/delete")
    public Result deptsDelete(@RequestParam("id") Long id) {
        int data = deptService.deleteById(id);
        System.out.println(data);
        return Result.success();
    }
}
