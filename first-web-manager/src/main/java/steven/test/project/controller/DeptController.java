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

@RestController
public class DeptController {
    private final DeptService deptService;

    // 构造器注入
    @Autowired
    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }
    // 控制层类 用于最先接受到前端请求 三层中的第一层

    // 获取列表分页
    @GetMapping("/depts")
    public Result list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String name
    ) {
        List<Dept> list = deptService.getDeptList(pageNum, pageSize, name);
        Long total = deptService.countDepts(name);
        return Result.successPage(list, total);
    }


    // 删除
    @DeleteMapping("/depts")
    public Result deptsDelete(@RequestParam("id") Long id) {
        int data = deptService.deleteById(id);
        System.out.println(data);
        return Result.success();
    }
}
