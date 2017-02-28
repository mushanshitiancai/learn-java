package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mazhibin on 16/12/28
 */
public class Permutations_46 {

    public static void main(String[] args) {
        Permutations_46 o = new Permutations_46();
        System.out.println(o.permute(new int[]{1, 2, 3}));
        System.out.println(o.permute(new int[]{1, 2, 3, 4}));

//        int[] ints = o.copyArray(new int[]{1, 2, 3, 4}, 1);
//        System.out.println(Arrays.toString(ints));
    }

    public List<List<Integer>> permute(int[] nums) {
        int[][] inner = inner(nums, nums.length);
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        for (int i = 0; i < inner.length; i++) {
            List<Integer> item = new ArrayList<Integer>();
            for (int j = 0; j < inner[i].length; j++) {
                item.add(inner[i][j]);
            }
            result.add(item);
        }

        return result;
    }

    public int[][] inner(int[] input, int length) {
//        System.out.println(String.format("inner start length=%d %s", length, Arrays.toString(input)));
        if (input == null || input.length == 0 || length == 0) {
            System.out.println("inner return null");
            return null;
        }
        if (length == 1) {
            int[][] result = new int[1][];
            result[0] = new int[input.length];
            result[0][0] = input[0];
//            System.out.println("inner return " + arrayToString(result));
            return result;
        }

        int[][] result = new int[factorial(length)][];
        int reti = 0;
        for (int i = 0; i < length; i++) {
            int cur = input[i];

//            System.arraycopy(input, i + 1, input, i, input.length - i - 1);
            input[i] = input[length - 1];
            int[][] nextArrResults = inner(input, length - 1);
            for (int j = 0; j < nextArrResults.length; j++) {
                result[reti] = nextArrResults[j];
                result[reti][length - 1] = cur;
                reti++;
            }
//            System.arraycopy(input,i,input,i+1,input.length - i - 1);
            input[length - 1] = input[i];
            input[i] = cur;
        }

//        System.out.println("inner return " + arrayToString(result));
        return result;
    }

    public int factorial(int i) {
        int result = 1;
        for (int j = i; j > 1; j--) {   
            result *= j;
        }
        return result;
    }

    public int[] copyArray(int[] arr, int ignoreIndex) {
        int[] result;
        if (ignoreIndex < 0 || ignoreIndex >= arr.length) {
            result = new int[arr.length];
            System.arraycopy(arr, 0, result, 0, arr.length);
        } else if (ignoreIndex == 0) {
            result = new int[arr.length - 1];
            System.arraycopy(arr, 1, result, 0, arr.length - 1);
        } else if (ignoreIndex == arr.length - 1) {
            result = new int[arr.length - 1];
            System.arraycopy(arr, 0, result, 0, arr.length - 1);
        } else {
            result = new int[arr.length - 1];
            System.arraycopy(arr, 0, result, 0, ignoreIndex);
            System.arraycopy(arr, ignoreIndex + 1, result, ignoreIndex, arr.length - ignoreIndex - 1);
        }

        return result;
    }

    public String arrayToString(int[][] arr) {
        List<String> resList = new ArrayList<String>();
        for (int[] ints : arr) {
            resList.add(Arrays.toString(ints));
        }
        return "[" + String.join(",", resList) + "]";
    }

    public int[][] inner_2(int[] input) {
        System.out.println("inner start " + Arrays.toString(input));
        if (input == null || input.length == 0) {
            return null;
        }
        int[][] result = new int[factorial(input.length)][];
        for (int i = 0; i < result.length; i++) {
            result[i] = new int[input.length];
        }
        if (input.length == 1) {
            result[0][0] = input[0];
            return result;
        }

        int reti = 0;
        for (int i = 0; i < input.length; i++) {
            int cur = input[i];

            int[] nextArr = copyArray(input, i);
            int[][] nextArrResults = inner_2(nextArr);
            System.out.println("inner nextArrResults " + arrayToString(nextArrResults));
            for (int j = 0; j < nextArrResults.length; j++) {
                result[reti][0] = cur;
                System.arraycopy(nextArrResults[j], 0, result[reti], 1, nextArrResults[j].length);
                reti++;
            }
        }

        return result;
    }


    public List<List<Integer>> inner_1(List<Integer> input) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (input == null || input.size() == 0) {
            return result;
        }
        if (input.size() == 1) {
            ArrayList<Integer> integers = new ArrayList<Integer>();
            integers.add(input.get(0));
            result.add(integers);
            return result;
        }

        for (int i = 0; i < input.size(); i++) {
            Integer integer = input.get(i);

            ArrayList<Integer> tempList = new ArrayList<Integer>(input);
            tempList.remove(i);
            List<List<Integer>> inner = inner_1(tempList);
            for (List<Integer> integers : inner) {
                integers.add(0, integer);
                result.add(integers);
            }
        }

        return result;
    }

}
