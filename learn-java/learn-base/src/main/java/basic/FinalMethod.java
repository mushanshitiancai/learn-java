package basic;

/**
 * Created by mazhibin on 16/9/6
 *
 * java - Is it a bad idea to declare a final static method? - Stack Overflow
 http://stackoverflow.com/questions/1932399/is-it-a-bad-idea-to-declare-a-final-static-method
 */
public class FinalMethod {
    public static void main(String[] args) {
        Foo.method();
        Bar.method();
    }
}
class Foo {
    public static /*final*/ void method() {
        System.out.println("in Foo");
    }
}

class Bar extends Foo {
    public static void method() {
        System.out.println("in Bar");
    }
}