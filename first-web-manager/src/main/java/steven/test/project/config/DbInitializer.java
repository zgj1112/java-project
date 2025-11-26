package steven.test.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import javax.sql.DataSource;
import java.sql.*;


// ================== 初始化数据库保证一键启动 ==================
@Component // 创建一个组件    扫描期间发生
public class DbInitializer {

    private final DataSource dataSource;

    @Autowired
    public DbInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void init() {
        try (Connection conn = dataSource.getConnection()) {

            // 1️⃣ 检查 & 创建数据库
            createDatabaseIfNotExists(conn);
            // 2. 切换到新数据库（非常关键！！）
            conn.setCatalog("myproject");

            if (!isTableExists(conn)) {
                System.out.println("dept 表不存在，正在创建并初始化数据...");
                ScriptUtils.executeSqlScript(conn, new ClassPathResource("sql/schema.sql"));
                ScriptUtils.executeSqlScript(conn, new ClassPathResource("sql/data.sql"));
                System.out.println("dept 表创建完成并已初始化数据。");
            } else {
                System.out.println("dept 表已存在，跳过初始化。");
            }
        } catch (SQLException e) {
            throw new RuntimeException("建表失败", e);
        }
    }

    private boolean isTableExists(Connection conn) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        try (ResultSet rs = metaData.getTables(conn.getCatalog(), null, "dept", new String[]{"TABLE"})) {
            return rs.next();
        }catch (SQLException e) {
            throw new RuntimeException("建库失败", e);
        }
    }
    /** 判断库是否存在，不存在则创建 */
    private void createDatabaseIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            String dbName = "myproject";
            String sql = "CREATE DATABASE IF NOT EXISTS `" + dbName + "` DEFAULT CHARACTER SET utf8mb4";
            stmt.executeUpdate(sql);

            System.out.println("数据库检查完成：" + dbName);
        }
    }

}
