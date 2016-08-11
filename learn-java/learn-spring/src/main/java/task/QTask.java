package task;

import java.util.concurrent.TimeUnit;

/**
 * Created by mazhibin on 16/8/5
 */
public class QTask {
    private int i = 0;;
    public void task() throws InterruptedException {
        System.out.println("q task:"+i++);

        if(i == 0)
            TimeUnit.MINUTES.sleep(1);
    }
}
