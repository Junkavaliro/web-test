package work.koreyoshi.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import work.koreyoshi.base.result.RestResult;
import work.koreyoshi.base.result.ResultMap;
import work.koreyoshi.project.file.service.impl.SimpleFileStorageServiceImpl;

/**
 * @author zhoujx
 */
@RestController
public class WeChatController {

    @Autowired
    private SimpleFileStorageServiceImpl fileStorageService;

    @RequestMapping("/token")
    public String getOpenId(String signature, String timestamp, String nonce, String echostr, String code) throws Exception {
        System.out.println("signature:" + signature);
        System.out.println("timestamp:" + timestamp);
        System.out.println("nonce:" + nonce);
        System.out.println("echostr:" + echostr);
        System.out.println(code);
        return echostr;
    }

    @PostMapping("/save/file")
    public RestResult getOpenId(MultipartFile file) {
        String id = fileStorageService.saveMultipartFile(file);
        return RestResult.ok(ResultMap.build().put("id", id));
    }
}
