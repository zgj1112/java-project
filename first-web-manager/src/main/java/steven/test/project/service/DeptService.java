package steven.test.project.service;

import steven.test.project.zhao.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> getDeptList(Integer pageNum, Integer pageSize, String name);

    Long countDepts(String name);

    void insertDept(Dept dept);

    void updateDept(Dept dept);

    void deleteById(Long id);
}
