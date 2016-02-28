package com.tobyn.base;

import java.lang.reflect.Method;

public class DemoBase {
	public static void main(String[] args) {
		
	}
	
	public static void runTest(Object o){
		Method[] methods = o.getClass().getMethods();
		for(int i=methods.length-1;i>=0;i--){
			Method m = methods[i];
			if(m.getName().matches("test\\d*")){
				System.out.println("---"+m.getName()+"---");
				try{
					m.invoke(o);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
