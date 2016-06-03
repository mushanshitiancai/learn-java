package nowcoder;

/**
 * Created by mazhibin on 16/6/3
 *
 * 请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 *
 * [替换空格](http://www.nowcoder.com/practice/4060ac7e3e404ad1a894ef3e17650423?tpId=13&tqId=11155&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)
 */
public class 替换空格 {

    public String replaceSpace(StringBuffer str) {
        StringBuffer sb = new StringBuffer(str.length());
        for(int i = 0;i<str.length();i++){
            char c = str.charAt(i);
            if(c == ' '){
                sb.append("%20");
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        替换空格 o = new 替换空格();

        String r = o.replaceSpace(new StringBuffer("We Are Happy"));
        System.out.println(r);
    }
}
