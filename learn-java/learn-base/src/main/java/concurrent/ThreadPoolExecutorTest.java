package concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by mazhibin on 16/11/17
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
    }

    // 最基本的使用
    public static void test1() throws InterruptedException {
        final ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<Runnable>(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 0L, TimeUnit.MILLISECONDS, arrayBlockingQueue, new RejectedHandler());

        threadPoolExecutor.execute(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread.sleep(100);
        threadPoolExecutor.getQueue();
    }

    // 使用自定义的RejectedExecutionHandler来处理被拒绝的任务
    public static void test2() {
        final ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<Runnable>(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 5, 0L, TimeUnit.MILLISECONDS, arrayBlockingQueue, new RejectedHandler());

        for (int i = 0; i < 30; i++) {
            final int index = i;
            threadPoolExecutor.submit(new Runnable() {
                public void run() {
                    System.out.println(index + " begin, queueSize=" + arrayBlockingQueue.size());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(index + " end, queueSize=" + arrayBlockingQueue.size());
                }
            });

            System.out.println("submit " + index);
        }
    }

    private static class RejectedHandler implements RejectedExecutionHandler {


        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 获取任务的返回结果
    synchronized public void test(){

    }
}
