package com.mushan.learn;

/**
 * Hello world!
 *
 */
public class App 
{

    public static int test(){
        int x = 1;
        try{
            int a = x/0;
            return x;
        }catch (Exception e){
            x=2;
            return x;
        }finally {
            x=3;
            //return x;
        }
    }

    public static void main( String[] args )
    {
        System.out.println(App.test());
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
