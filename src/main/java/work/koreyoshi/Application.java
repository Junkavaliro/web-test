package work.koreyoshi;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author zhoujx
 */
@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("system-message-send-pool-%d").build();
    ExecutorService singleThreadPool = new ThreadPoolExecutor(
            1,
            2,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024),
            threadFactory,
            new ThreadPoolExecutor.AbortPolicy());


    @Scheduled(cron = "0 0/1 * * * ?")
    public void sendTemplate() {
        singleThreadPool.execute(() -> {
            System.out.println(new Date());
        });
    }
}
