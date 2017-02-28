package concurrent;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by mazhibin on 16/11/21
 */
public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test_FutureTask();
//        test_executor();
        test();
    }

    // 使用FutureTask来运行Callable
    public static void test_FutureTask() throws ExecutionException, InterruptedException {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(5000);
                return new Random().nextInt();
            }
        };

        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();

        Integer integer = futureTask.get();
        System.out.println(integer);
    }

    // 使用Executor来原型Callable
    public static void test_executor() throws ExecutionException, InterruptedException {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(5000);
                return new Random().nextInt();
            }
        };

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> submit = executorService.submit(callable);

        Integer integer = submit.get();
        System.out.println(integer);
    }

    public static void test(){
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(threadPool);
        for(int i = 1; i < 5; i++) {
            final int taskID = i;
            cs.submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                    Thread.sleep(new Random().nextInt(100));
                    return taskID;
                }
            });
        }
        // 可能做一些事情
        for(int i = 1; i < 5; i++) {
            try {
                System.out.println(cs.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("end");
        threadPool.shutdown();
    }
}
