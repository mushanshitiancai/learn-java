package nowcoder;

/**
 * Created by mazhibin on 16/6/4
 */
public class 斐波那契数列 {

    public int Fibonacci(int n) {
        if(n == 0 || n == 1) return n;

        int left=0,right=1;
        int result = 0;

        for (int i = 0; i < n - 1; i++) {
            result = left+right;
            left = right;
            right = result;
        }

        return result;
    }

    /**
     * 递归解法
     */
    public int Fibonacci_1(int n) {
        if(n == 0 || n == 1) return 1;

        return Fibonacci(n-1)+Fibonacci(n-2);
    }

    public static void main(String[] args) {
        斐波那契数列 o = new 斐波那契数列();
        int r = o.Fibonacci(3);
        System.out.println(r);
    }
}
