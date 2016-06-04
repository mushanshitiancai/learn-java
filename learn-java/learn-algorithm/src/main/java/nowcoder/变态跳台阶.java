package nowcoder;

/**
 * Created by mazhibin on 16/6/4
 *
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *
 * [变态跳台阶](http://www.nowcoder.com/practice/22243d016f6b47f2a6928b4313c85387?tpId=13&tqId=11162&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)
 */
public class 变态跳台阶 {
    public int JumpFloorII(int target) {
        return 1<<--target;
    }


    public int JumpFloorII_1(int target) {
        if(target <= 2) return target;

        int[] result = new int[target+1];
        result[1] = 1;
        result[2] = 2;

        for (int i = 3; i <= target; i++) {
            for (int j = 1; j < i; j++) {
                result[i] += result[j];
            }
            result[i] += 1;
        }

        return result[target];
    }

    public static void main(String[] args) {
        变态跳台阶 o = new 变态跳台阶();
        int r = o.JumpFloorII(4);
        System.out.println(r);
    }
}
