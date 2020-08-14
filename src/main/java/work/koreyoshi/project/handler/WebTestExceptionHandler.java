package work.koreyoshi.project.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import work.koreyoshi.base.exception.SendException;
import work.koreyoshi.base.exception.handler.BaseExceptionHandler;
import work.koreyoshi.base.result.RestResult;

/**
 * @author zhoujx
 */
@RestControllerAdvice
@Slf4j
public class WebTestExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(value = SendException.class)
    public RestResult sendMailHandler(SendException e) {
        log.info("异常处理：{}", e.getMessage());
        return RestResult.error(e);
    }

}
