package steven.test.project.zhao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 实体类 用于映射sql表字段
public class Dept {
    private Integer id;
    private String name;
    private LocalTime createTime;
    private LocalTime updateTime;
}
