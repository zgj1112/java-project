package steven.test.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value; // 引入 Value 注解
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import steven.test.project.zhao.Result;

// import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

//============== 上传接口  ===========
@Tag(name = "文件管理", description = "文件上传接口")
@RestController
public class FileController {

    // 1. 使用 @Value 读取 application.yml 中的配置
    @Value("${file.upload-path}")
    private String uploadDir;

    @PostMapping("/file/upload")
    @Operation(summary = "上传单个文件")
    public Result uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error(400, "上传失败，请选择文件");
        }

        try {
            // 2. 使用读取到的配置路径
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 3. 生成新的文件名，防止重名覆盖
            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String newFileName = UUID.randomUUID() + fileExtension;

            // 4. 构造完整的文件路径
            Path filePath = uploadPath.resolve(newFileName);

            // 5. 将文件写入磁盘
            file.transferTo(filePath.toFile());

            // 6. 返回成功信息和文件路径
            String fileUrl = "/files/" + newFileName; // 假设前端通过 /files/ 访问
            return Result.success(fileUrl);

        } catch (IOException e) {
            // 实际项目中，这里应该记录 ERROR 日志
            // e.printStackTrace();
            return Result.error(500, "文件上传失败：" + e.getMessage());
        }
    }
}
