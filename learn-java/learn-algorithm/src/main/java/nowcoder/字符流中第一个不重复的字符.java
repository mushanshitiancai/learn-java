package nowcoder;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * Created by mazhibin on 16/6/5
 *
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 *
 * 输出描述:
 * 如果当前字符流没有存在出现一次的字符，返回#字符。
 *
 * [字符流中第一个不重复的字符](http://www.nowcoder.com/practice/00de97733b8e4f97a3fb5c680ee10720?tpId=13&tqId=11207&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)
 *
 * 1. 不要用现成数据结构,比如map
 * 2. ascii的范围要注意,a不是0
 */
public class 字符流中第一个不重复的字符 {

    StringBuilder sb = new StringBuilder();
    int[] flags = new int[256];

    //Insert one char from string stream
    public void Insert(char ch)
    {
        sb.append(ch);
        flags[ch]++;
    }
    //return the first appearance once char in current string stream
    public char FirstAppearingOnce()
    {
        for (int i = 0; i < sb.length(); i++) {
            if(flags[sb.charAt(i)] == 1){
                return sb.charAt(i);
            }
        }
        return '#';
    }

    public static void main(String[] args) {
        字符流中第一个不重复的字符 o = new 字符流中第一个不重复的字符();

        o.Insert('g');
        System.out.println(o.FirstAppearingOnce());
        o.Insert('o');
        System.out.println(o.FirstAppearingOnce());
        o.Insert('o');
        System.out.println(o.FirstAppearingOnce());
        o.Insert('g');
        System.out.println(o.FirstAppearingOnce());
        o.Insert('l');
        System.out.println(o.FirstAppearingOnce());
        o.Insert('e');
        System.out.println(o.FirstAppearingOnce());
    }
}
