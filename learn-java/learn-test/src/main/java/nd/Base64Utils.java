package nd;

import java.nio.charset.Charset;

public final class Base64Utils {
    public static String encode(String data) {
        return encode(data.getBytes(Charset.forName("UTF-8")));
    }
    public static String encode(byte[] data) {
        return org.apache.commons.codec.binary.Base64.encodeBase64String(data);
    }

    public static byte[] decode(String data) {
        return org.apache.commons.codec.binary.Base64.decodeBase64(data);
    }
    public static String decodeToString(String data) {
        return new String(decode(data),Charset.forName("UTF-8"));
    }
}
