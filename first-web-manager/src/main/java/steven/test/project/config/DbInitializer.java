package steven.test.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;


// ================== 初始化数据库保证一键启动 ==================
@Component
public class DbInitializer {

    private final DataSource dataSource;

    @Autowired
    public DbInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void init() {
        try (Connection conn = dataSource.getConnection()) {
            if (!isTableExists(conn)) {
                System.out.println("dept 表不存在，正在创建并初始化数据...");
                ScriptUtils.executeSqlScript(conn, new ClassPathResource("sql/schema.sql"));
                ScriptUtils.executeSqlScript(conn, new ClassPathResource("sql/data.sql"));
                System.out.println("dept 表创建完成并已初始化数据。");
            } else {
                System.out.println("dept 表已存在，跳过初始化。");
            }
        } catch (SQLException e) {
            throw new RuntimeException("初始化数据库失败", e);
        }
    }

    private boolean isTableExists(Connection conn) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        try (ResultSet rs = metaData.getTables(conn.getCatalog(), null, "dept", new String[]{"TABLE"})) {
            return rs.next();
        }
    }
}
