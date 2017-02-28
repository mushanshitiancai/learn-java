package thread;

/**
 * Created by mazhibin on 17/2/22
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal<Integer> local = new ThreadLocal<>();

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                if (local.get() == null) {
                    local.set(1);
                }else{
                    local.set(local.get()+1);
                }

                System.out.println(local.get());
            }).start();
        }
    }

}
