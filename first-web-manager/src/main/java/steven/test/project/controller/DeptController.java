package steven.test.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import steven.test.project.service.DeptService;
import steven.test.project.zhao.Dept;
import steven.test.project.zhao.Result;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;
    // 控制层类 用于最先接受到前端请求 三层中的第一层
    @RequestMapping("/depts")
    public Result list() {
        List<Dept> list = deptService.getTable();
        return Result.success(list);
    }
}
