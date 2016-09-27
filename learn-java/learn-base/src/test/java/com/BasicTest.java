package com;

import org.junit.Test;

/**
 * Created by mazhibin on 16/5/11
 */
public class BasicTest {

    @Test
    public void staticTest(){
        StaticTest.echo();
    }

    @Test
    public void tryReturnTest(){
        System.out.println(tryReturn());
    }

    public StringBuilder tryReturn(){
        StringBuilder sb = new StringBuilder();
        try {
            if(true) throw new RuntimeException();
            return sb.append("1");
        }catch (Exception e){
            return sb.append("2");
        }finally {
            return sb.append("3");
        }
    }

    @Test
    public void subStringTest(){

    }
}


class StaticTest{


    static{
        System.out.println("static");
        x=5;
    }

    static int x = 10;

    static void echo(){
        System.out.println(x);
    }
}