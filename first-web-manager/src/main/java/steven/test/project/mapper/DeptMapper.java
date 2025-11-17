package steven.test.project.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import steven.test.project.zhao.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {
    // 数据访问层 三层中最后一步到数据库
    // 获取列表分页 offset第多少条开始 pageSize每页多少条 name模糊查询
    List<Dept> getDeptList(@Param("offset") Integer offset,
                           @Param("pageSize") Integer pageSize,
                           @Param("name") String name);

    // 获取列表总数
    Long countDepts(@Param("name") String name);

    // 新增
    int insertDept(@Param("dept") Dept dept);

    // 修改
    int updateDept(@Param("dept") Dept dept);

    // 删除
    int deleteById(@Param("id") Long id);
}
