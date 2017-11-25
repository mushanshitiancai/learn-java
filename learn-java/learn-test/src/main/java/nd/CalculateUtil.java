package nd;

import java.util.Random;


public class CalculateUtil {

    /**
     * 取某个范围的任意数
     * @param min
     * @param max
     * @return
     */
    public static int getNext(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    /**
     * 生成sum位随机码
     * @return
     */
    public static String generateDigitRandomCode(int sum){
        Random rd = new Random();
        String n = "";
        int getNum;
        do {
            getNum = Math.abs(rd.nextInt()) % 10 + 48;// 产生数字0-9的随机数
            char num1 = (char) getNum;
            String dn = Character.toString(num1);
            n += dn;
        } while (n.length() < sum);

        return n;
    }

    /**
     * 生成sum位数字字母随机码
     * @param sum
     * @return
     */
    public static String generateMixRandomCode(int sum){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for(int i = 0 ; i < sum; ++i){
            int number = random.nextInt(62);//[0,62)

            sb.append(str.charAt(number));
        }
        return sb.toString();
    }


}
