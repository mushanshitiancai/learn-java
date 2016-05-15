/**
 * Created by mazhibin on 16/5/15
 */

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目:三个线程按顺序执行
 */
public class ThreeSequentialThread {
    public static void main(String[] args) throws InterruptedException, IOException {
//        new Method1().run();
//        new Method2().run();
//        new Method3().run();
        new Method4().run();
    }
}

/**
 * 解法1: 使用Object的wait/notifyAll
 */
class Method1 {
    class Status {
        private int cur = 2;

        public synchronized void do1() {
            try {
                while (cur != 1) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("do 1");
            cur = 2;
            notifyAll();
        }

        public synchronized void do2() {
            try {
                while (cur != 2) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("do 2");
            cur = 3;
            notifyAll();
        }

        public synchronized void do3() {
            try {
                while (cur != 3) {
                    wait();
                }
            } catch (InterruptedException e) {
                System.out.println("interrupted 3");
            }
            System.out.println("do 3");
            cur = 1;
            notifyAll();
        }
    }

    public void run() throws InterruptedException {
        final Status status = new Status();

        Runnable runnable1 = new Runnable() {
            public void run() {
                status.do1();
            }
        };

        Runnable runnable2 = new Runnable() {
            public void run() {
                status.do2();
            }
        };

        Runnable runnable3 = new Runnable() {
            public void run() {
                status.do3();
            }
        };

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(runnable1);
        service.execute(runnable2);
        service.execute(runnable3);
        service.execute(runnable1);
        service.execute(runnable2);
        service.execute(runnable3);
        service.execute(runnable1);
        service.execute(runnable2);
        service.execute(runnable3);
        service.execute(runnable1);
        service.execute(runnable2);
        service.execute(runnable3);

        service.execute(runnable3);

        TimeUnit.SECONDS.sleep(1);
        service.shutdownNow();
    }
}

/**
 * 解法2: 使用Condition的await/signalAll
 */
class Method2 {


    int cur = 1;

    public void run() {
        final Lock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (cur != 1) {
                        System.out.println("await 1");
                        condition.await();
                    }
                    System.out.println("do 1");
                    cur = 2;
                    condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (cur != 2) {
                        System.out.println("await 2");
                        condition.await();
                    }
                    System.out.println("do 2");
                    cur = 3;
                    condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (cur != 3) {
                        System.out.println("await 3");
                        condition.await();
                    }
                    System.out.println("do 3");
                    cur = 1;
                    condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        };

        ExecutorService service = Executors.newCachedThreadPool();

        Thread tt = new Thread(){
            @Override
            public void run() {
                System.out.println("tt");
            }
        };
//        service.execute(tt);
//        service.execute(tt);
//        service.execute(tt);
//        service.execute(tt);

        service.execute(t1);
        service.execute(t1);
        service.execute(t1);

        service.execute(t2);
        service.execute(t2);
        service.execute(t2);

        service.execute(t3);
        service.execute(t3);
        service.execute(t3);



        service.shutdown();
    }
}

/**
 * 解法3: 使用BlockingQueue
 */
class Method3 {

    public void run() throws InterruptedException {

//        final BlockingQueue<Integer> q12 = new LinkedBlockingQueue<Integer>();
//        final BlockingQueue<Integer> q23 = new LinkedBlockingQueue<Integer>();

        final BlockingQueue<Integer> q12 = new ArrayBlockingQueue<Integer>(1);
        final BlockingQueue<Integer> q23 = new ArrayBlockingQueue<Integer>(1);

        Thread t1 = new Thread(){
            @Override
            public void run() {
                System.out.println("do 1");
                try {
                    q12.put(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                try {
                    int v = q12.take();
                    System.out.println("do "+v);
                    q23.add(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t3 = new Thread(){
            @Override
            public void run() {
                try {
                    int v = q23.take();
                    System.out.println("do " + v);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ExecutorService service = Executors.newCachedThreadPool();

        service.execute(t1);
        service.execute(t1);

        TimeUnit.SECONDS.sleep(1);

        service.execute(t3);
        service.execute(t2);
        service.execute(t1);
        service.execute(t3);
        service.execute(t2);
        service.execute(t3);
        service.execute(t2);




        service.shutdown();
    }
}

/**
 * 解法4: 使用管道
 */
class Method4 {

    public void run() throws IOException {
        final PipedWriter writer12 = new PipedWriter();
        final PipedReader reader12 = new PipedReader(writer12);
        final PipedWriter writer23 = new PipedWriter();
        final PipedReader reader23 = new PipedReader(writer23);

        Thread t1 = new Thread(){
            @Override
            public void run() {
                System.out.println("do 1");
                try {
                    writer12.write('2');
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                try {
                    char v = (char)reader12.read();
                    System.out.println("do "+v);
                    writer23.write('3');
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t3 = new Thread(){
            @Override
            public void run() {
                try {
                    char v = (char)reader23.read();
                    System.out.println("do " + v);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(t3);
        service.execute(t2);
        service.execute(t1);
        service.shutdown();
    }
}