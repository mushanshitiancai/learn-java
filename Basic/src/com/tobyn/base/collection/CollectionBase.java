package com.tobyn.base.collection;

import java.util.List;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import com.tobyn.base.DemoBase;

public class CollectionBase extends DemoBase {
	public static void main(String[] args) {
		CollectionBase o = new CollectionBase();
		DemoBase.runTest(o);
	}
	
	/**
	 * 如果你直接实用Arrays.asList的结果，你需要注意，他的本质还是一个数组，
	 * 所以如果你使用了add或delete，会报错`java.lang.UnsupportedOperationException`
	 */
	public void test1(){
		Collection<String> c = new ArrayList<>();
		String[] strArr = {"hello","world"};
		List<String> strArrList = Arrays.asList(strArr);
		
		System.out.println(strArrList);
		strArrList.add("fuck");
	}
	
	/**
	 * 书中说底下的代码会报错，然而并不会。
	 */
	public void test2(){
		List<A> a1 = Arrays.asList(new AA1(),new AA2(),new B());
		List<A> a2 = Arrays.asList(new BB1(),new BB2());
	}
	
	/**
	 * array和collection对于输出的支持
	 */
	public void test3(){
		String[] strArr = {"A","B","C"};
		List<String> strList = new ArrayList<>();
		strList.addAll(Arrays.asList("A","B","C"));
		
		System.out.println(strArr);
		System.out.println(Arrays.toString(strArr));
		System.out.println(strList);
	}
	
	/**
	 * 结合的equal是比较每一个元素后的结果
	 */
	public void test4(){
		List<String> strList = new ArrayList<>();
		strList.addAll(Arrays.asList("A","B","C"));
		List<String> strList2 = new ArrayList<>();
		strList2.addAll(Arrays.asList("A","B"));
		
		System.out.println(strList == strList2);
		System.out.println(strList.equals(strList2));
		
		strList2.add("C");
		System.out.println(strList.equals(strList2));
	}
	
	/**
	 * remove和removeAll的区别
	 */
	public void test5(){
		List<String> strList = new LinkedList<>();
		strList.addAll(Arrays.asList("A","B","C","C","C","A"));
		
		strList.remove("C");
		System.out.println(strList);
		strList.removeAll(Arrays.asList("C"));
		System.out.println(strList);
	}
	
	/**
	 * 容器中保存的是对这个对象的引用
	 */
	public void test6(){
		IntContainer i = new IntContainer();
		
		List<IntContainer> list1 = new ArrayList<>();
		List<IntContainer> list2 = new ArrayList<>();
		
		list1.add(i);
		list2.add(i);
		
		i.num = 99;
		System.out.println("两个容器中保存的是对这个对象的引用：");
		System.out.println(list1);
		System.out.println(list2);
	}
	
	
}

class A{}
class B extends A{}
class AA1 extends A{}
class AA2 extends A{}
class BB1 extends B{}
class BB2 extends B{}

class IntContainer{
	public int num = 0;
	
	public String toString(){
		return ""+this.num;
	}
}