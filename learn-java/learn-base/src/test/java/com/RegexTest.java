package com;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mazhibin on 16/4/28
 */
public class RegexTest {

    /**
     * 判断整个输入字符串是否匹配正则
     */
    @Test
    public void matchesTest(){
        String regex = "\\d+";
        Pattern p = Pattern.compile(regex);
        String text;
        Matcher matcher;

        text = "a 2 c 12";
        matcher = p.matcher(text);
        Assert.assertEquals(text.matches(regex),false);
        Assert.assertEquals(matcher.matches(),false);

        text = "12a";
        matcher = p.matcher(text);
        Assert.assertEquals(text.matches(regex), false);
        Assert.assertEquals(matcher.matches(), false);

        text = "12";
        matcher = p.matcher(text);
        Assert.assertEquals(text.matches(regex),true);
        Assert.assertEquals(matcher.matches(),true);
    }

    @Test
    public void lookingAtTest(){
        String regex = "\\d+";
        Pattern p = Pattern.compile(regex);
        String text;
        Matcher matcher;

        text = "hello 12";
        matcher = p.matcher(text);
        Assert.assertEquals(matcher.lookingAt(),false);

        text = "12 hello";
        Assert.assertEquals(p.matcher(text).lookingAt(), true);
    }

    @Test
    public void findTest(){
        String text = "a 2 c 12";
        Pattern p = Pattern.compile("\\d(\\d)?");
        Matcher matcher = p.matcher(text);

        Assert.assertEquals(matcher.find(),true);
        Assert.assertEquals(matcher.find(),true);
        Assert.assertEquals(matcher.find(),false);
        Assert.assertEquals(matcher.find(),false);

        matcher.reset();
        Assert.assertEquals(matcher.find(0), true);
        Assert.assertEquals(matcher.find(0), true);
        Assert.assertEquals(matcher.find(0), true);
        Assert.assertEquals(matcher.find(0), true);


        matcher.reset();
        Assert.assertEquals(matcher.find(), true);
        Assert.assertEquals(matcher.group(), "2");
        Assert.assertEquals(matcher.start(), 2);
        Assert.assertEquals(matcher.end(), 3);
        Assert.assertEquals(matcher.group(1),null);
        Assert.assertEquals(matcher.start(1),-1);
        Assert.assertEquals(matcher.end(1),-1);
    }

    @Test
    public void find2Test(){
        Pattern pattern = Pattern.compile("\\d{6}((\\d{4})(\\d{2})(\\d{2}))[\\dX]{4}");
        Matcher matcher = pattern.matcher("350104199305015417");

        System.out.println(matcher.groupCount());
        while(matcher.find()){
            System.out.println(matcher.group());  //整个身份证号
            System.out.println(matcher.group(1)); //生日
            System.out.println(matcher.group(2)); //生日-年
            System.out.println(matcher.group(3)); //生日-月
            System.out.println(matcher.group(4)); //生日-日
        }
    }

    @Test
    public void groupTest(){
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher("a1a");

        matcher.find();
        System.out.println(matcher.group());
        System.out.println(matcher.start());
        System.out.println(matcher.end());
    }

    @Test
    public void usePatternTest(){
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher("a1b2c");

        matcher.find();
        System.out.println(matcher.group());

        matcher.usePattern(Pattern.compile("[a-z]"));
        System.out.println(matcher.group());
        System.out.println(matcher.start());

        matcher.find();
        System.out.println(matcher.group());
    }

    @Test
    public void replaceTest(){
        String text = "a_1_b_23";
        Pattern p = Pattern.compile("\\d(\\d)?");
        Matcher matcher = p.matcher(text);

//        StringBuffer sb = new StringBuffer();
//        if(matcher.find()){
//            matcher.appendReplacement(sb,"first");
//            System.out.println(sb);
//            matcher.appendTail(sb);
//            System.out.println(sb);
//        }
    }

    @Test
    public void anchoringTest(){
        String text = "a_1_b_23";
        Pattern p = Pattern.compile("\\d(\\d)?");
        Matcher matcher = p.matcher(text);

        matcher.region(7, 8);
        System.out.println(matcher.regionStart());
        System.out.println(matcher.regionEnd());

//        System.out.println(matcher.replaceFirst("R"));

        System.out.println(matcher.regionStart());
        System.out.println(matcher.regionEnd());

        System.out.println(matcher.find());
        System.out.println(matcher.group());

        matcher.usePattern(Pattern.compile("\\d$"));
        System.out.println(matcher.regionStart());
        System.out.println(matcher.regionEnd());

        matcher.reset();
        matcher.region(2, 3);
        matcher.useAnchoringBounds(false);
        System.out.println(matcher.find());
//        System.out.println(matcher.group());
        System.out.println(matcher.hitEnd());
        System.out.println(matcher.requireEnd());
    }

    @Test
    public void quoteTest(){
        String q = Pattern.quote("(\\E1)");

        Assert.assertEquals(q, "\\Q(\\E\\\\E\\Q1)\\E");
    }

    @Test
    public void splitTest(){
        Pattern p = Pattern.compile("\\d");

        Assert.assertArrayEquals(p.split("a_1_b_23"),new String[]{"a_","_b_"});

        Assert.assertArrayEquals(p.split("a_1_b_23",0),new String[]{"a_","_b_"});
        Assert.assertArrayEquals(p.split("a_1_b_23",1),new String[]{"a_1_b_23"});
        Assert.assertArrayEquals(p.split("a_1_b_23",2),new String[]{"a_","_b_23"});
        Assert.assertArrayEquals(p.split("a_1_b_23",3),new String[]{"a_","_b_","3"});
        Assert.assertArrayEquals(p.split("a_1_b_23",4),new String[]{"a_","_b_","",""});
        Assert.assertArrayEquals(p.split("a_1_b_23",5),new String[]{"a_","_b_","",""});
        Assert.assertArrayEquals(p.split("a_1_b_23",6),new String[]{"a_","_b_","",""});
        Assert.assertArrayEquals(p.split("a_1_b_23",-1),new String[]{"a_","_b_","",""});
        Assert.assertArrayEquals(p.split("a_1_b_23",-2),new String[]{"a_","_b_","",""});
    }
}
