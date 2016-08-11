package task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by mazhibin on 16/8/5
 */
@Component("task")
public class Task {

    @Scheduled(cron = "1 * * * * ?")
    public void task(){
        System.out.println("task");
    }

    @Scheduled(fixedRate = 1000)
    public void task1() throws InterruptedException {
        System.out.println("task1");
        TimeUnit.SECONDS.sleep(1);
    }

    @Scheduled(fixedDelay = 1000)
    public void task2() throws InterruptedException {
        System.out.println("task2");
        TimeUnit.SECONDS.sleep(1);
    }
}
