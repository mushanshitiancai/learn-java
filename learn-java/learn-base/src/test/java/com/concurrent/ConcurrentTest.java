package com.concurrent;

import org.junit.Test;

/**
 * Created by mazhibin on 16/11/17
 */
public class ConcurrentTest {

    @Test
    public void test() {

        System.out.println(to(100));
        System.out.println(to(12345));
    }

    public String to(int num) {
        String unit = "十百千万";
        String result = "";

        int cur = num;
        int index = 0;
        while (cur > 0) {
            int j = cur % 10;

            if(j != 0) {
                if (index != 0) {
                    result = j + "" + unit.charAt(index - 1) + result;
                } else {
                    result = j + result;
                }
            }

            index++;
            cur = cur / 10;
        }

        return result;
    }
}
