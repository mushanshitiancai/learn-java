/**
 * Created by mazhibin on 16/5/11
 */
public class StaticTest {
    static{
        System.out.println("StaticTest static");
    }

    public StaticTest() {
        System.out.println("StaticTest constructor");
    }

    public static void main(String[] args) {
        new Child();
    }
}

class Single{
    static {
        System.out.println("Single static");
    }

    public Single(String str){
        System.out.println("Single constructor - "+str);
    }
}

class Child extends StaticTest{
    static Single staticSingle = new Single("Child-static-field");
    Single single = new Single("Child-field");

    static {
        System.out.println("Child-static1");
        staticSingle =null;
        System.out.println("Child-static2");
        System.out.println(staticSingle);
//        System.out.println(staticSingle2);      // illegal forward reference
        staticSingle2 =null;                      // 在<3>执行前变量就存在了.
        System.out.println("Child-static3");
    }

    static Single staticSingle2 = new Single("Child-static-field2");  // <3>
    Single single2 = new Single("Child-field2");

    public Child(){
        System.out.println("Child constructor");
    }
}

