package nowcoder;

/**
 * Created by mazhibin on 16/6/4
 *
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *
 * [跳台阶](http://www.nowcoder.com/practice/8c82a5b80378478f9484d87d1c5f12a4?tpId=13&tqId=11161&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)
 */
public class 跳台阶 {
    public int JumpFloor(int target) {
        if(target <= 2) return target;

        int i = 1,j = 2,result = 0;

        for (int k = 0; k < target - 2; k++) {
            result = i+j;
            i = j;
            j = result;
        }

        return result;
    }

    public static void main(String[] args) {
        跳台阶 o = new 跳台阶();
        int r = o.JumpFloor(4);
        System.out.println(r);
    }
}
