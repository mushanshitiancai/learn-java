package nowcoder;

/**
 * Created by mazhibin on 16/6/3
 *
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 *
 * [旋转数组的最小数字](http://www.nowcoder.com/practice/9f3231a991af4f55b95579b44b7a01ba?tpId=13&tqId=11159&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)
 */
public class 旋转数组的最小数字 {

    /**
     * 参考书上解法
     */
    public int minNumberInRotateArray(int [] array) {
        if(array == null || array.length == 0) return 0;

        int i = 0;
        int j = array.length-1;
        int mid = i;

        while(array[i] >= array[j]){
            if(j-i == 1){
                mid = j;
                break;
            }

            mid = (i+j)/2;

            if(array[i] == array[mid] && array[j] == array[mid]){
                int min = array[0];
                for (int k = 0; k < array.length; k++) {
                    if(array[k] < min) min = array[k];
                }
                return min;
            }

            if(array[mid] >= array[i]){
                i = mid;
            }else if(array[mid] <= array[j]){
                j = mid;
            }
        }

        return array[mid];
    }

    public static void main(String[] args) {
        旋转数组的最小数字 o = new 旋转数组的最小数字();

        int r = o.minNumberInRotateArray(new int[]{3,4,5,1,2});
        System.out.println(r);

        r = o.minNumberInRotateArray(new int[]{2,3,4,5,1});
        System.out.println(r);

        r = o.minNumberInRotateArray(new int[]{2,1,2,2,2});
        System.out.println(r);
    }
}
