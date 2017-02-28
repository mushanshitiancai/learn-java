package concurrent;

/**
 * Created by mazhibin on 16/11/20
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
//        test_interrupt();
        test_join();
    }

    public static void test_interrupt() throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("begin sleep");
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    System.out.println("interrupted: " + Thread.interrupted());
                }

                try {
                    System.out.println("begin sleep 2");
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                System.out.println("interrupted: " + Thread.interrupted());
                System.out.println("end sleep");
            }
        };


        Thread thread = new Thread(runnable);
        thread.start();
        thread.interrupt();

        Thread.sleep(1000);
        System.out.println("interrupted: " + thread.isInterrupted());
    }

    public static void test_join() throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("begin sleep");
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    System.out.println("interrupted: " + Thread.interrupted());
                }

                System.out.println("end sleep");
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        thread.join();
        System.out.println("main end");
    }
}
