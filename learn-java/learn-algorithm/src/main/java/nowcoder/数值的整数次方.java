package nowcoder;

/**
 * Created by mazhibin on 16/6/4
 *
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 *
 * [数值的整数次方](http://www.nowcoder.com/practice/1a834e5e3e1a4b7ba251417554e07c00?tpId=13&tqId=11165&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)
 */
public class 数值的整数次方 {

    public double Power(double base, int exponent) {
        double result = 1;

        if(exponent >= 0){
            while (exponent != 0){
                result *= base;
                exponent--;
            }
        }else{
            while (exponent != 0){
                result *= base;
                exponent++;
            }
            result = 1/result;
        }

        return result;
    }

    public static void main(String[] args) {
        数值的整数次方 o = new 数值的整数次方();
        double r = o.Power(2,3);
        System.out.println(r);
        r = o.Power(2,-3);
        System.out.println(r);
        r = o.Power(0,-3);
        System.out.println(r);
    }
}
