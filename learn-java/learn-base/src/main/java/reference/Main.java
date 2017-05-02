package reference;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.lang.ref.WeakReference;

/**
 * Created by mazhibin on 17/3/4
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        WeakReference<String> s = new WeakReference<String>("hello");
        
        System.gc();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
        }
        System.out.println(s.get());
    }
}
