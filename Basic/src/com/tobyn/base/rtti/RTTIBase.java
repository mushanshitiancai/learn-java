package com.tobyn.base.rtti;

import com.tobyn.base.DemoBase;

public class RTTIBase {
	public static void main(String[] args) {
		RTTIBase o = new RTTIBase();
		DemoBase.runTest(o);
	}
	
	public void test1() {
		System.out.println("获取Class对象引用的方法：");
		Class class1 = null;
		try {
			class1 = Class.forName("com.tobyn.base.rtti.RTTIBase");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("1. Class.forName(\"com.tobyn.base.rtti.RTTIBase\")     :"+class1);
		
		Class class2 = RTTIBase.class;
		System.out.println("2. RTTIBase.class     :"+class2);
		
		Class class3 = new RTTIBase().getClass();
		System.out.println("3. new RTTIBase().getClass()    :"+class3);
	}
}
