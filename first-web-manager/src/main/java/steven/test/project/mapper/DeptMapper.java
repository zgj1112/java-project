package steven.test.project.mapper;

import org.apache.ibatis.annotations.Mapper;
import steven.test.project.zhao.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {
    //数据访问层 三层中最后一步到数据库
    List<Dept> getTable();
}
