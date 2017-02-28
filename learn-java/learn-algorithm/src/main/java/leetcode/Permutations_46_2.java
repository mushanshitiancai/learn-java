package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mazhibin on 16/12/28
 */
public class Permutations_46_2 {

    public static void main(String[] args) {
        Permutations_46_2 o = new Permutations_46_2();
        System.out.println(o.permute(new int[]{1, 2, 3}));
        System.out.println(o.permute(new int[]{1, 2, 3, 4}));

    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        inner(nums,0,result);
        return result;
    }
    
    public void inner(int[] nums, int i, List<List<Integer>> result){
        if(i == nums.length){
            List<Integer> item = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                item.add(nums[j]);    
            }
            result.add(item);
        }

        for (int j = i; j < nums.length; j++) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            inner(nums, i+1, result);
            temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
