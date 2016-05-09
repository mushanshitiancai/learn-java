package com;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by mazhibin on 16/4/28
 */
public class FileTest {


    //获取分隔符
    @Test
    public void separatorTest(){
        System.out.println(File.separator);
        System.out.println(File.pathSeparator);
    }

    @Test
    public void listRootsTest(){
        System.out.println(Arrays.toString(File.listRoots()));
    }

    //File.list获取文件列表
    @Test
    public void fileListTest(){
        File file = new File("/Users/mazhibin/project/xxx");
        String[] files = file.list();

        System.out.println(Arrays.toString(files));

        files = file.list(new FilenameFilter() {
            Pattern pattern = Pattern.compile(".*\\.json");

            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        });
        System.out.println(Arrays.toString(files));
    }

    @Test
    public void mkdirTest(){
        File file = new File("/Users/mazhibin/project/xxx/c/d");
        boolean f = file.mkdirs();
        System.out.println(f);
    }

}
