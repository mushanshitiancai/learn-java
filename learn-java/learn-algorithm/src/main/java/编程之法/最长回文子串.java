package 编程之法;

/**
 * Created by mazhibin on 16/6/5
 */
public class 最长回文子串 {

    public int LongestPalindrome(String str){
        int i = 1;
        int size = 0;

        while(i<=str.length()-2){
            int n = 0;
            while((i-1-n)>=0 && (i+1+n)<=str.length()-1 && str.charAt(i-1-n) == str.charAt(i+1+n)){
                n++;
            }
            if(n>size) size = n*2+1;

            n = 0;
            while((i-n)>=0 && (i+1+n)<=str.length()-1 && str.charAt(i-n) == str.charAt(i+1+n)){
                n++;
            }
            if(n>size) size = n*2;

            i++;
        }
        return size;
    }

    public static void main(String[] args) {
        最长回文子串 o = new 最长回文子串();
        int r = o.LongestPalindrome("cbabcd");
        System.out.println(r);
        r = o.LongestPalindrome("cbaabcd");
        System.out.println(r);
        r = o.LongestPalindrome("c");
        System.out.println(r);
    }

}
