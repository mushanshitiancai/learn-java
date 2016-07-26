package thread;

import java.util.concurrent.*;

/**
 * Created by mazhibin on 16/7/3
 */
public class Test {

    Runnable task1 = new Runnable() {
        public void run() {
            System.out.println("task1");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    Runnable task2 = new Runnable() {
        public void run() {
            System.out.println("task2");
        }
    };

    public static void main(String[] args) {

    }
}
