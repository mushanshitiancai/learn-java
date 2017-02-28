package concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by mazhibin on 16/11/18
 */
public class ForkJoinTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(new AddTask());

        System.out.println(submit.get());

    }

    private static class AddTask extends RecursiveTask<Integer>{

        protected Integer compute() {
            return 1;
        }
    }
}
