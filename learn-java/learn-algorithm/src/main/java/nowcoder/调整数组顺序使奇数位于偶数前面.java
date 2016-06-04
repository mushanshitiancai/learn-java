package nowcoder;

import java.util.Arrays;

/**
 * Created by mazhibin on 16/6/4
 *
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 *
 * [调整数组顺序使奇数位于偶数前面](http://www.nowcoder.com/practice/beb5aa231adc45b2a5dcc5b62c93f593?tpId=13&tqId=11166&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)
 */
public class 调整数组顺序使奇数位于偶数前面 {
    public void reOrderArray(int [] array) {
        int i = 0;
        int j = array.length-1;

        while(i < j){
            while(array[i]%2==1 && i<j) i++;
            while(array[j]%2==0 && i<j) j--;

            if(i >= j)break;

            int t = array[i];
            array[i++] = array[j];
            array[j--] = t;
        }
    }

    public static void main(String[] args) {
        调整数组顺序使奇数位于偶数前面 o = new 调整数组顺序使奇数位于偶数前面();

        int[] array = new int[]{1,2,3,4,5,6,7};
        o.reOrderArray(array);
        System.out.println(Arrays.toString(array));

    }
}
