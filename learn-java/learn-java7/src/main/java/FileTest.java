import java.io.File;
import java.io.IOException;

/**
 * Created by mazhibin on 16/11/19
 */
public class FileTest {

    public static void main(String[] args) throws IOException {
        test1();
    }

    // File获取子文件会列出软链接的文件么？
    public static void test1() throws IOException {
        File xxx = new File("/Users/mazhibin/project/xxx");
        File[] files = xxx.listFiles();

        for (File file : files) {
            System.out.println(file + "  " + file.getCanonicalFile());
        }
    }
}
