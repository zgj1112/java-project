package steven.test.project.service;

import steven.test.project.zhao.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> getDeptList(Integer pageNum, Integer pageSize, String name);
    Long countDepts(String name);
    int deleteById(Long id);
}
