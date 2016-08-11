package task;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

/**
 * Created by mazhibin on 16/8/5
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
//        new Main().test1();
//        new Main().test2();
        LocalDate localDate = LocalDate.now().minusDays(1);

        int i = Integer.valueOf(localDate.toString().replaceAll("-",""));
        System.out.println(i);
    }


    public void test1(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/auto.xml");

//        Task task = (Task) ctx.getBean("task");
//        task.task();

        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test2(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/qtask.xml");

//        Task task = (Task) ctx.getBean("task");
//        task.task();

        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

