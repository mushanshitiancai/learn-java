package com.tobyn.base.flow;

import com.tobyn.base.DemoBase;

public class FlowBase {
	public static void main(String[] args) {
		FlowBase o = new FlowBase();
		DemoBase.runTest(o);
	}
	
	public void test1(){
		System.out.println("foreach语句，冒号后面的语句只会执行一次：");
		for(int i:getIntArr()){
			System.out.println(i);
		}
	}
	
	public void test2(){
		System.out.println("Label只能放在迭代语句前？--YES");	
		label1:
		for(int i=0;i<5;i++){
			System.out.println(i);
			if(i == 3) break label1;
		}
	}
	
	public void test3(){
		System.out.println("switch语句每个case后能跟多个语句么？--能");
		int a = 1;
		switch(a){
		case 1:
			System.out.println("statment1");
			System.out.println("statment2");
			System.out.println("statment3");
			break;
		}
	}
	
	public int[] getIntArr(){
		System.out.println("get");
		int[] arr = {1,2,3};
		return arr;
	}
}
