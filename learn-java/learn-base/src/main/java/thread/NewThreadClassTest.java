package thread;

import java.util.concurrent.*;

/**
 * Created by mazhibin on 16/5/21
 *
 * SE5中的新构件
 */
public class NewThreadClassTest {
    public static void main(String[] args) {
//        new CountDownLatchTest().run();
//        new CyclicBarrierTest().run();
//        new DelayQueueTest().run();
        new ScheduledExecutorServiceTest().run();
    }
}

/**
 * 测试 CountDownLatch
 */
class CountDownLatchTest{

    CountDownLatch latch = new CountDownLatch(2);

    Thread t1 = new Thread(){
        @Override
        public void run() {
            while (!Thread.interrupted()){
                System.out.println("do 1");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        }
    };

    Thread t2 = new Thread(){
        @Override
        public void run() {
            while (!Thread.interrupted()){
                System.out.println("do 2");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();  // latch=0后,再countDown没有任何影响
            }
        }
    };

    Thread t3 = new Thread(){
        @Override
        public void run() {
            while(!Thread.interrupted()){
                try {
                    latch.await();       // 如果latch变为0后,这段总是会跳过
                    System.out.println("do 3." + latch.getCount());
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public void run(){
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(t1);
        service.execute(t2);
        service.execute(t3);
        service.shutdown();
    }
}

/**
 * 测试 CyclicBarrier
 *
 * 和CountDownLatch相比,CyclicBarrier是可以重复使用的
 */
class CyclicBarrierTest{

    ExecutorService service;
    int count = 0;

    CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
        public void run() {
            System.out.println("in barrier!"+count);
            if(++count >= 2){
                service.shutdownNow();
            }
        }
    });

    Thread t1 = new Thread(){
        @Override
        public void run() {
            while (!Thread.interrupted()){
                try {
                    System.out.println("do 1");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    Thread t2 = new Thread(){
        @Override
        public void run() {
            while (!Thread.interrupted()){
                try {
                    System.out.println("do 2");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public void run(){
        // 每执行t1,t2后就会执行CyclicBarrier中的线程,然后重复
        service = Executors.newCachedThreadPool();
        service.execute(t1);
        service.execute(t2);
        service.shutdown();
    }
}

/**
 * 测试 DelayQueue
 */
class DelayQueueTest{
    DelayQueue<DelayedTask> delayQueue = new DelayQueue<DelayedTask>();

    class DelayedTask implements Delayed,Runnable{
        private final long delay;
        private final long trigger;

        DelayedTask(int delayInMilliseconds){
            delay = delayInMilliseconds;
            trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delayInMilliseconds, TimeUnit.MILLISECONDS);
        }

        public long getDelay(TimeUnit unit) {
            return unit.convert(trigger - System.nanoTime(),TimeUnit.NANOSECONDS);
        }

        public int compareTo(Delayed o) {
            long td = getDelay(TimeUnit.NANOSECONDS);
            long od = o.getDelay(TimeUnit.NANOSECONDS);

            if(td < od){
                return -1;
            }else if(td > od){
                return 1;
            }
            return 0;
        }

        public void run() {
            System.out.println(delay);
        }
    }

    class EndSentinel extends DelayedTask{
        ExecutorService service;

        EndSentinel(int delayInMilliseconds,ExecutorService service) {
            super(delayInMilliseconds);
            this.service = service;
        }

        @Override
        public void run() {
            service.shutdownNow();
        }
    }

    class DelayedTaskConsumer implements Runnable{

        public void run() {
            while(!Thread.interrupted()){
                try {
                    delayQueue.take().run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void run(){
        ExecutorService service = Executors.newCachedThreadPool();

        DelayedTask task = new DelayedTask(100);
        delayQueue.put(task);

        task = new DelayedTask(200);
        delayQueue.put(task);

        task = new DelayedTask(50);
        delayQueue.put(task);

        task = new EndSentinel(300,service);
        delayQueue.put(task);

        service.execute(new DelayedTaskConsumer());
    }
}

/**
 * 测试 ScheduledExecutorService
 */
class ScheduledExecutorServiceTest {

    public void run(){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1); //如果线程池不够用,时间到了也得等着

        service.schedule(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("do 2");
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },1, TimeUnit.SECONDS);

        service.schedule(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("do 1");
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 1, TimeUnit.SECONDS);

        service.shutdown();
    }
}