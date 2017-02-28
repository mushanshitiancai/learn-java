package string;

import java.util.Arrays;

/**
 * Created by mazhibin on 16/10/19
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("aa".matches(""));

        System.out.println(Arrays.toString("abc".split("a")));
        System.out.println(Arrays.toString("abc".split("b")));
        System.out.println(Arrays.toString("abc".split("c")));
        System.out.println(Arrays.toString("abc".split("")));
        System.out.println(Arrays.toString("abc".split("/")));

        System.out.println();

        System.out.println(Arrays.toString("".split("/")));
    }
}
