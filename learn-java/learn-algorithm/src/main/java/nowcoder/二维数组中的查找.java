package nowcoder;

/**
 * Created by mazhibin on 16/5/15
 *
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 输入描述:
 * array： 待查找的二维数组
 * target：查找的数字
 *
 * http://www.nowcoder.com/practice/abc3fe2ce8e146608e868a70efebf62e?tpId=13&tqId=11154&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */
public class 二维数组中的查找 {

    /**
     * 解法1:超时
     */
    public boolean Find_1(int [][] array,int target) {
        int i=0,j=0;

        while(i < array.length && j < array[0].length){
            int x = array[i][j];
            if(target == x){
                return true;
            }
            else if(target > x){
                i++;j++;
            }else{
                for(int ii=0;ii<=i;ii++){
                    if(target == array[ii][j]) return true;
                }
                for(int jj=0;jj<=j;jj++){
                    if(target == array[i][jj]) return true;
                }
            }
        }
        return false;
    }

    /**
     *  解法2:超时
     */
    public boolean Find_2(int [][] array,int target) {
        int i=0,j=0;

        while(i < array.length && j < array[0].length){
            int x = array[i][j];
            if(target == x){
                return true;
            }
            else if(target > x){
                i++;j++;
            }else{
                int begin = 0,end = i;
                while(begin <= end){
                    int mid = (begin+end)/2;

                    if(target < array[mid][j]){
                        begin = mid - 1;
                    }else if(target > array[mid][j]){
                        end = mid + 1;
                    }else{
                        return true;
                    }
                }

                begin = 0;end = j;
                while(begin <= end){
                    int mid = (begin+end)/2;

                    if(target < array[i][mid]){
                        begin = mid - 1;
                    }else if(target > array[i][mid]){
                        end = mid + 1;
                    }else{
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 正确解法
     * 参考思路: [二维数组中的查找_牛客网](http://www.nowcoder.com/questionTerminal/abc3fe2ce8e146608e868a70efebf62e)
     */
    public boolean Find(int [][] array,int target) {
        int i = array.length-1;
        int j = 0;

        while(i>=0 && j<=array[0].length-1){
            int x = array[i][j];

            if(target > x){
                j++;
            }else if(target < x){
                i--;
            }else{
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        二维数组中的查找 find = new 二维数组中的查找();

//        int[] input = {1,2,3,4,4,5};
//        int pos = find.binarySearch(input,4);
//        System.out.println(pos);

        int[][] input =
                {
                        {1, 2, 3},
                        {2, 3, 4},
                        {3, 4, 5}
                };
        boolean r = find.Find(input, 2);
        System.out.println(r);
        r = find.Find(input, 5);
        System.out.println(r);
        r = find.Find(input, 6);
        System.out.println(r);
    }

    /**
     * 二分查找
     *
     * [你真的会二分查找吗？](http://blog.csdn.net/int64ago/article/details/7425727/)
     */
    public int binarySearch(int[] array,int target){
        int begin = 0;
        int end = array.length-1;


        while(begin <= end){
            int mid = (begin+end)/2;

            if(target < array[mid]){
                end = mid - 1;
            }else if(target > array[mid]){
                begin = mid +1;
            }else{
                return mid;
            }
        }
        return -1;
    }
}
