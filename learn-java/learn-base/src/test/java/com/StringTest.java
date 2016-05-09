package com;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by mazhibin on 16/4/28
 */
public class StringTest {

    @Test
    public void splitTest(){
        Assert.assertArrayEquals("o1oo".split("o",-1),new String[]{"","1","",""});
    }

    @Test
    public void matchTest(){
        Assert.assertEquals("a1b".matches("\\d"), false);
    }

    @Test
    public void replaceTest(){
        Assert.assertEquals("a1b1".replaceFirst("\\d","U"),"aUb1");
        Assert.assertEquals("a1b1".replaceAll("\\d","U"),"aUbU");
    }
}
