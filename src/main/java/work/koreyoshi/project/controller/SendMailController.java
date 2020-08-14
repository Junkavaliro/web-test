package work.koreyoshi.project.controller;

import work.koreyoshi.project.mail.annotation.SendMail;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.koreyoshi.base.result.RestResult;

/**
 * @author Administrator
 */
@RestController
public class SendMailController {

    @RequestMapping("send")
    @SendMail(tos = "#0", title = "#1", msg = "#2")
    public RestResult send(String[] tos, String title, String msg){
        return RestResult.ok();
    }

}
