package com.tobyn.base;

public class LanguageBase {
	public String test1 = "sss";
	
	static{
		System.out.println("static");
	}
	
	public static void main(String[] args) {
		LanguageBase o = new LanguageBase();
		DemoBase.runTest(o);
	}
	
	public void test1(){
		System.out.println("成员变量和成员方法可以同名：");
		System.out.println(test1);
	}
}
