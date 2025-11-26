package steven.test.project.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import steven.test.project.mapper.DeptMapper;
import steven.test.project.service.DeptService;
import steven.test.project.zhao.Dept;

import java.util.List;

@Service
public class DeptServiceImp implements DeptService {

    private final DeptMapper deptMapper;

    @Autowired
    public DeptServiceImp(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    @Override
    public List<Dept> getDeptList(Integer pageNum, Integer pageSize, String name) {
        // 1. 创建分页对象
        Page<Dept> page = new Page<>(pageNum, pageSize);

        // 2. 创建查询条件构造器
        LambdaQueryWrapper<Dept> wrapper = new LambdaQueryWrapper<>();
        // 3. 模糊查询条件：如果 name 不为空，则添加 name 字段的模糊查询
        if (name != null && !name.isEmpty()) {
            wrapper.like(Dept::getName, name);
        }
        // 4. 执行分页查询
        Page<Dept> resultPage = deptMapper.selectPage(page, wrapper);

        // 5. 返回记录列表
        return resultPage.getRecords();
    }

    @Override
    public Long countDepts(String name) {
        // 使用 MP 的 selectCount 方法进行计数
        LambdaQueryWrapper<Dept> wrapper = new LambdaQueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            wrapper.like(Dept::getName, name);
        }
        return deptMapper.selectCount(wrapper);
    }

    @Override
    public void insertDept(Dept dept) {
        // 使用 MP 的 insert 方法
        deptMapper.insert(dept);
    }

    @Override
    public void updateDept(Dept dept) {
        // 使用 MP 的 updateById 方法
        deptMapper.updateById(dept);
    }

    @Override
    public void deleteById(Long id) {
        // 使用 MP 的 deleteById 方法
        deptMapper.deleteById(id);
    }
}
