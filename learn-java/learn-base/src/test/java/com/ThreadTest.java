package com;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by mazhibin on 16/5/9
 */
public class ThreadTest {

    @Test
    public void threadTest(){
        Assert.assertEquals(Thread.currentThread().getPriority(),Thread.NORM_PRIORITY);
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
    }

    @Test
    public void executorTest(){

    }

    public static void main(String[] args) {
        ThreadTest test = new ThreadTest();
//        test.yieldTest1();
//        test.daemonTest();
//        test.joinTest();
//        test.exceptionTest1();
//        test.exceptionTest2();
//        test.synchronizedTest();
//        test.lockTest();
//        test.tryLockTest();
//        test.threadLocalTest();
        test.interruptedTest();
    }

    //yield的用法
    public void yieldTest1(){
        new Thread(){
            @Override
            public void run() {
                for(int i=0;i<100;i++){
                    System.out.println("A"+i);
                    Thread.yield();            //没加yield,基本上A执行完了才会执行B
                                                // 加了后,线程切换效果明显
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                for(int i=0;i<100;i++) {
                    System.out.println("B"+i);
//                    Thread.yield();
                }
            }
        }.start();
    }

    public void daemonTest(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(i);
                }
            }
        };
        thread.setDaemon(true); //设置为后台线程
        thread.start();
    }

    public void joinTest(){
        final Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i+" "+isInterrupted());
                    try {
                        sleep(2);
                    } catch (InterruptedException e) {
//                        e.printStackTrace();
                        System.out.println("interrupted");
                    }
                    yield();
                }
            }
        };

        new Thread(){
            @Override
            public void run() {
                System.out.println("begin");

                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("end");
            }
        }.start();

        thread.start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    // 线程中的方法无法被捕获
    public void exceptionTest1(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                throw new RuntimeException();
            }
        };

        try {
            thread.start();
        }catch (Exception e){
            System.out.println("hello");
            e.printStackTrace();
        }
    }

    public void exceptionTest2(){
        Runnable runnable = new Runnable() {
            public void run() {
                System.out.println("throw");
                throw new RuntimeException();
            }
        };

        Thread thread = new Thread(runnable);
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("fuck");
                System.out.println(e.getLocalizedMessage());
            }
        });
//        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
//
//            public void uncaughtException(Thread t, Throwable e) {
//                System.out.println("hello");
//                System.out.println(e);
//            }
//        });

        thread.start();

//        ExecutorService service = Executors.newCachedThreadPool(new ThreadFactory() {
//            public Thread newThread(Runnable r) {
//                Thread t = new Thread(r);
//                t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
//
//                    public void uncaughtException(Thread t, Throwable e) {
//                        System.out.println("hello");
//                        System.out.println(e);
//                    }
//                });
//                return t;
//            }
//        });
//        service.execute(runnable);
    }

    public void synchronizedTest(){
        class A{
            private int i = 0;

            synchronized public int next(){
                System.out.println("+");
                i++;
                Thread.yield();
                i++;
                return i;
            }
        }

        final A a = new A();

        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    for (int j = 0; j < 1; j++) {
                        System.out.println("-");
                        System.out.println(a.next());
                    }
                }
            }.start();
        }
    }

    // lock test
    public void lockTest(){
        class A{
            ReentrantLock lock = new ReentrantLock();
            private int i = 0;
            public int next(){
                lock.lock();
                try {
                    i++;
                    Thread.yield();
                    i++;
                    return i;
                }finally {
                    lock.unlock();
                }
            }
        }

        final A a = new A();
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    System.out.println(a.next());
                }
            }.start();
        }
    }

    public void tryLockTest(){
        class A{
            ReentrantLock lock = new ReentrantLock();
            private int i = 0;
            public int next(){
                boolean isLock = false;
                try {
                    isLock = lock.tryLock(1, TimeUnit.SECONDS);
                    System.out.println("isLock="+isLock+" isLocked="+lock.isLocked());
                    i++;
                    Thread.yield();
                    i++;
                    return i;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return i;
                } finally {
                    if (isLock){
//                        System.out.println("unlock");
                        lock.unlock();
                    }
                }
            }
        }

        final A a = new A();
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    System.out.println(a.next());
                }
            }.start();
        }

    }

    public void threadLocalTest(){
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    int r = ThreadLocalTest.value.get();
                    System.out.println(r);
                }
            }.start();
        }
    }

    public void interruptedTest(){
        Thread t = new Thread(){
            @Override
            public void run() {
                boolean a = false;
                while(!Thread.interrupted()){
                    a  = !a;
                }
            }
        };

        t.start();
        t.interrupt();
    }
}

class ThreadLocalTest {
    static Random random = new Random(100);

//    static Integer integer = random.nextInt(100);
    static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            System.out.println("static");
            return random.nextInt(100);
        }
    };
//    static ThreadLocal<Integer> value = new ThreadLocal<Integer>();
//    static {
//        value.set(random.nextInt(100));
//        System.out.println("static");
//    }
}