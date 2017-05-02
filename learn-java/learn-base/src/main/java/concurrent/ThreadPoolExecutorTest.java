package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by mazhibin on 16/11/17
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
//        callableThrowTest();
        poolThrowTest();
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


    public static void threadThrowTest() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

//        for (int i = 0; i < 10; i++) {
//            final int ii = i;
//            executorService.submit(() -> {
//                
//                Thread.sleep(100);
//                
//                if(ii == 6) {
//                    throw new IllegalArgumentException();
//                }
//                System.out.println(ii);
//                return ii;
//            });
//        }

//        for (int i = 0; i < 10; i++) {
//            final int ii = i;
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(ii/0);
//                }
//            });
//        }

        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println(1 / 0);
            }
        };
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(e);
            }
        });
        thread.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getState());


    }

    public static void callableThrowTest() {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1 / 0;
            }
        };

        FutureTask<Integer> task = new FutureTask<>(callable);
        Thread thread = new Thread(task);

        thread.start();
        try {
            Integer integer = task.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void poolThrowTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                System.out.println(1/0);
            }
        });

//        List<Future<?>> futures = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            final int ii = i;
//            Future<?> submit = executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(100*ii);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(ii);
//
//                    if (ii == 6) {
//                        System.out.println(1 / 0);
//                    }
//                }
//            });
//
//            futures.add(submit);
//        }
//
//        for (Future<?> future : futures) {
//            try {
//                future.get();
//            } catch (InterruptedException e) {
//                System.out.println("1" + e);
//            } catch (ExecutionException e) {
//                System.out.println("2" + e);
//                e.printStackTrace();
//            }
//        }
    }
}
