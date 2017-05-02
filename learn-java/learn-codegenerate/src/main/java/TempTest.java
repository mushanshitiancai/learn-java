import com.google.common.base.Strings;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;

import java.nio.charset.Charset;

/**
 * Created by mazhibin on 17/2/27
 */
public class TempTest {
    
    public static void main(String[] args) throws Exception {
        String s = "D#B5CB#BAEA#CEB0";
        System.out.println( Hex.encodeHexString( "邓".getBytes(Charset.forName("GBK")) ) );


        System.out.println(Strings.padStart("00",8, 'x'));
    }

}
