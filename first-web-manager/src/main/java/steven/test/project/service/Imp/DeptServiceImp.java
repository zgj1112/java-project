package steven.test.project.service.Imp;

import org.springframework.stereotype.Service;
import steven.test.project.service.DeptService;
import steven.test.project.zhao.Dept;
// import org.springframework.beans.factory.annotation.Autowired;
import steven.test.project.mapper.DeptMapper;

import java.util.List;

@Service
public class DeptServiceImp implements DeptService {
    private final DeptMapper deptMapper;

    public DeptServiceImp(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    @Override
    public List<Dept> getDeptList(Integer pageNum, Integer pageSize, String name) {
        int offset = (pageNum - 1) * pageSize;
        return deptMapper.getDeptList(offset, pageSize, name);
    }

    @Override
    public Long countDepts(String name) {
        return deptMapper.countDepts(name);
    }

    @Override
    public void insertDept(Dept dept) {
        int rows = deptMapper.insertDept(dept);
        if (rows == 0) {
            throw new RuntimeException("新增部门失败");
        }
        // return rows;
    }

    @Override
    public void updateDept(Dept dept) {
        int rows = deptMapper.updateDept(dept);
        if (rows == 0) {
            throw new RuntimeException("更新部门失败，记录不存在");
        }
        // return rows;
    }

    @Override
    public void deleteById(Long id) {
        int rows = deptMapper.deleteById(id);
        if (rows == 0) {
            throw new RuntimeException("删除失败，记录不存在");
        }
        // return rows;
    }
}
