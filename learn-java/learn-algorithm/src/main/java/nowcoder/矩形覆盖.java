package nowcoder;

/**
 * Created by mazhibin on 16/6/4
 *
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 *
 * [矩形覆盖](http://www.nowcoder.com/practice/72a5a919508a4251859fb2cfb987a0e6?tpId=13&tqId=11163&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)
 */
public class 矩形覆盖 {
    public int RectCover(int target) {
        if(target <= 2) return target;

        int i = 1,j = 2,result = 0;

        for (int k = 0; k <target-2; k++) {
            result = i+j;
            i = j;
            j = result;
        }

        return result;
    }
}
