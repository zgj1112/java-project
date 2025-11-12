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
    public int deleteById(Long id) {
        return deptMapper.deleteById(id);
    }
}
