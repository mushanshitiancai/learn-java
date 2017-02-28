package bytecode;

/**
 * Created by mazhibin on 17/1/10
 */
public class Add {

    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public int add_const_1(int a) {
        return a + 1;
    }

    public int add_const_100(int a) {
        return a + 100;
    }

    public int add_const_256(int a) {
        return a + 256;
    }

    public int add_local(int a) {
        int b = 1;
        int j = 1000000;
        long l1 = 1;
        long l2 = 1999999999999999999L;
        double d = 2.2;
        
        return a + b;
    }
}
