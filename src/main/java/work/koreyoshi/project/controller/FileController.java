package work.koreyoshi.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import work.koreyoshi.base.result.RestResult;
import work.koreyoshi.base.result.ResultMap;
import work.koreyoshi.project.common.model.FileStorage;
import work.koreyoshi.project.file.service.impl.SimpleFileStorageServiceImpl;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author zhoujx
 */
@RestController
public class FileController {

    @Autowired
    private SimpleFileStorageServiceImpl fileStorageService;

    @PostMapping("/files")
    public RestResult save(MultipartFile file) {
        String id = fileStorageService.saveMultipartFile(file);
        return RestResult.ok(ResultMap.build().put("id", id));
    }

    @GetMapping("/files")
    public RestResult files() {
        List<FileStorage> all = fileStorageService.findAll();
        return RestResult.ok(ResultMap.build().put("files", all));
    }

    @GetMapping("/files/{id}")
    public void files(@PathVariable String id, HttpServletResponse response) throws IOException {
        File file = fileStorageService.getFileById(id);
        ServletOutputStream outputStream = response.getOutputStream();
        FileInputStream inputStream = new FileInputStream(file);
        outputStream.write(inputStream.read());
    }

    @DeleteMapping("/files/{id}")
    public RestResult deleteFiles(@PathVariable String id) {
        Boolean deleted = fileStorageService.deleteById(id);
        return RestResult.ok(ResultMap.build().put("删除文件结果", deleted));
    }
}
