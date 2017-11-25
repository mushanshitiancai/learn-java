package nd;

import java.security.MessageDigest;

/**
 * Created by mazhibin on 2017/5/19 0019.
 */
public class Md5 {

    public static void main(String[] args) {
        String s = encryptMD5_ND("123456");
        System.out.println(s);

        String mac = getMacToken("localhost:9003", "/v0.1/csession/avatar/users/921219", "GET", "LVrHuyPHxS", "F3FF6E1203AA79112B74E95AA30B03687DB83769F679D5379BF9F08600FC2104");
        System.out.println(mac);
    }

    public static String getMacToken(String host,String requestUri,String method,String macKey, String accessToken){
        String nonce = System.currentTimeMillis()+":"+ CalculateUtil.generateMixRandomCode(8);
        StringBuilder sbRawMac = new StringBuilder();
        sbRawMac.append(nonce);
        sbRawMac.append("\n");
        sbRawMac.append(method);
        sbRawMac.append("\n");
        sbRawMac.append(requestUri);
        sbRawMac.append("\n");
        sbRawMac.append(host);
        sbRawMac.append("\n");
        String mac = EncryptUtil.encryptHMac256(sbRawMac.toString(), macKey);
        StringBuilder auth = new StringBuilder();
        auth.append("MAC id=\"");
        auth.append(accessToken);
        auth.append("\",nonce=\"");
        auth.append(nonce);
        auth.append("\",mac=\"");
        auth.append(mac);
        auth.append("\"");
        return auth.toString();
    }


    /**
     * 混淆MD5加密
     *
     * @param content
     * @return
     */
    public static String encryptMD5_ND(String content) {
        String resultString = "";
        String appkey = "fdjf,jkgfkl";

        byte[] a = appkey.getBytes();
        byte[] datSource = content.getBytes();
        byte[] b = new byte[a.length + 4 + datSource.length];

        int i;
        for (i = 0; i < datSource.length; i++) {
            b[i] = datSource[i];
        }

        b[i++] = (byte) 163;
        b[i++] = (byte) 172;
        b[i++] = (byte) 161;

        b[i++] = (byte) 163;

        for (int k = 0; k < a.length; k++) {
            b[i] = a[k];
            i++;
        }

        try {
            MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
            md5.update(b);

            // 将得到的字节数组变成字符串返回
            resultString = byteArrayToHexString(md5.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultString.toLowerCase();
    }

    public static final String KEY_MD5 = "MD5";

    /**
     * 转换字节数组为十六进制字符串
     *
     * @param b 字节数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 将一个字节转化成十六进制形式的字符串
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    //十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};


}


