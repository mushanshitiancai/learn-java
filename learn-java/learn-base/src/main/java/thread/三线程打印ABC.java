package thread;

/**
 * Created by mazhibin on 16/7/4
 */
public class 三线程打印ABC {
    static String cur = "A";
    static int time = 30;

    static void incCur(){
        if(cur.equals("A")){
            cur = "B";
        }else if(cur.equals("B")){
            cur = "C";
        }else if(cur.equals("C")){
            cur = "A";
        }
    }

    static class PrintThread extends Thread{
        String id;
        int curTime = 0;

        PrintThread(String id){
            this.id = id;
        }

        public void run() {
            while(time > 0) {
                synchronized (cur) {
                    if (cur.equals(id)) {
                        System.out.println(time+":"+cur+curTime);
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        incCur();
                        time--;
                        curTime++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
//        new PrintThread("A").start();
//        new PrintThread("B").start();
//        new PrintThread("C").start();

        new Object(){
          synchronized void  echo (){
              try {
                  wait();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.out.println("fuck");
          }
        }.echo();
    }
}
