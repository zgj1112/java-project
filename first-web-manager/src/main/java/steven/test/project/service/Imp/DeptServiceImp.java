package steven.test.project.service.Imp;

import org.springframework.stereotype.Service;
import steven.test.project.service.DeptService;
import steven.test.project.zhao.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import steven.test.project.mapper.DeptMapper;

import java.util.List;

@Service
public class DeptServiceImp implements DeptService {
    @Autowired
    private DeptMapper deptMapper; // ✅ 正确注入 Mapper

    @Override
    public List<Dept> getTable() {
        return deptMapper.getTable();
    }
}
