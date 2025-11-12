package steven.test.project.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import steven.test.project.zhao.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {
    // 数据访问层 三层中最后一步到数据库
    // 获取列表分页
    List<Dept> getDeptList(@Param("offset") Integer offset,
                           @Param("pageSize") Integer pageSize,
                           @Param("name") String name);

    // 获取列表总数
    Long countDepts(@Param("name") String name);

    // 删除
    int deleteById(@Param("id") Long id);
}
