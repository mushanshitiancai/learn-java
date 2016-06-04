package nowcoder;

/**
 * Created by mazhibin on 16/6/4
 *
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 *
 * [二进制中1的个数](http://www.nowcoder.com/practice/8ee967e43c2c4ec193b040ea7fbb10b8?tpId=13&tqId=11164&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)
 *
 * 位移逻辑:
 * 1. 右移:左边补充标志位,即0补0,1补1
 * 2. 左移:没有额外逻辑
 */
public class 二进制中1的个数 {

    public int  NumberOf1(int n) {
        int result = 0;

        while(n!=0){
            n = n & n-1;
            result++;
        }

        return result;
    }


    public int  NumberOf1_1(int n) {
        int result = 0;
        int i = 1;

        while(i != 0){
            if((n & i) != 0){
                result++;
            }
            i = i<<1;
        }

        return result;
    }

    public static void main(String[] args) {
        二进制中1的个数 o = new 二进制中1的个数();

//        System.out.println(-1>>1);
//        System.out.println(-1>>2);
//        System.out.println(-1<<1);
//        System.out.println(1>>1);
//        System.out.println(Integer.MIN_VALUE<<1);
//        System.out.println(-7<<1);
//        System.out.println(0x40000000<<1);
//        System.out.println(0xa0000000);
//        System.out.println(0xa0000000<<1);
//        System.out.println(0xa0000000<<2);
//        System.out.println(1<<31);
        System.out.println(0x80000000);
//        System.exit(0);

        int r;
        r = o.NumberOf1(1);
        System.out.println(r);
        r = o.NumberOf1(2);
        System.out.println(r);
        r = o.NumberOf1(3);
        System.out.println(r);
        r = o.NumberOf1(Integer.MAX_VALUE);
        System.out.println(r);
        r = o.NumberOf1(-1);
        System.out.println(r);
        r = o.NumberOf1(-2147483648);
        System.out.println(r);
    }
}
