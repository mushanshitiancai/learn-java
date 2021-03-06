package nd;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class EncryptUtil {

    public static final String KEY_SHA = "SHA";
    public static final String KEY_MD5 = "MD5";
    private final static String KEY_DES = "DES";
    public static final String KEY_HMD5_256 = "HmacSHA256";

    //十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 标准MD5加密
     *
     * @param content
     * @return
     */
    public static String encryptMD5_STD(String content) {
        String resultString = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
            md5.update(content.getBytes());

            // 将得到的字节数组变成字符串返回
            resultString = byteArrayToHexString(md5.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultString.toLowerCase();
    }

    /**
     * MD5+Salt加密
     *
     * @param content
     * @return
     */
    public static String encryptMD5_Salt(String content) {

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
            resultString = new HexBinaryAdapter().marshal(md5.digest());//转为十六进制的字符串
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultString.toLowerCase();
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

    public static byte[] encryptSHA(String content) throws Exception {

        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(content.getBytes());

        return sha.digest();

    }

    public static byte[] encryptDes(byte[] src, byte[] key) throws Exception {

        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(KEY_DES);
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        return cipher.doFinal(src);
    }

    public static byte[] decryptDes(byte[] src, byte[] key) throws Exception {

        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(KEY_DES);
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        return cipher.doFinal(src);

    }


    /**
     * 转换字节数组为十六进制字符串
     *
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

    /**
     * HMac256加密
     * @param content
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptHMac256(String content, String key) {

        String resultString = "";
        try {
            // 还原密钥
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            // 实例化Mac
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            // 初始化mac
            mac.init(secretKey);
            // 执行消息摘要
            byte[] digest = mac.doFinal(content.getBytes());
//	        resultString = new String(Base64.byteArrayToBase64(digest));
            resultString = Base64Utils.encode(digest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  resultString;
    }
}
