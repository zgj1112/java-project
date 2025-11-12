package steven.test.project.zhao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
// 实体类 用于映射sql表字段
public class Dept {
    private Integer id;
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 序列化 LocalTime
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
