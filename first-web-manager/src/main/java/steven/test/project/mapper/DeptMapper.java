package steven.test.project.mapper;

import org.apache.ibatis.annotations.Mapper;
import steven.test.project.zhao.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {
    List<Dept> getTable();
}
