package com.tobyn.base;

import java.util.Arrays;

public class ArrayBase {
	public static void main(String[] args) {
		ArrayBase o = new ArrayBase();
		DemoBase.runTest(o);
	}
	
	public void test1(){
		System.out.println("建立一个非基本类型的数据，默认填充的是null么?--是的");
		Integer[] arr = new Integer[10];
		System.out.println(Arrays.toString(arr));
	}
	
	public void test2(){
		System.out.println("建立基本类型数组的三种方式：");
		
		int[] arr1 = {1,2,3};
		int[] arr2 = new int[3];
		int[] arr3 = new int[]{1,2,3}; // 注意不要指定数组大小
		
		System.out.println(Arrays.toString(arr1));
		System.out.println(Arrays.toString(arr2));
		System.out.println(Arrays.toString(arr3));
	}
	
	public void test3(){
		System.out.println("建立对象类型数组的三种方式：");
		
		Integer[] arr1 = {1,2,3};
		Integer[] arr2 = new Integer[3];
		Integer[] arr3 = new Integer[]{1,2,3}; // 注意不要指定数组大小
		
		System.out.println(Arrays.toString(arr1));
		System.out.println(Arrays.toString(arr2));
		System.out.println(Arrays.toString(arr3));
		
	}
}
