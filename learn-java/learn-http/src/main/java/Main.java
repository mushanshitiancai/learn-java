import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mazhibin on 17/2/7
 */
public class Main {
    public static void main(String[] args) throws IOException {
        get();

        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("param1", "value 1"));
        formparams.add(new BasicNameValuePair("param2", "value2"));
        String format = URLEncodedUtils.format(formparams, HTTP.DEF_CONTENT_CHARSET);
        System.out.println(format);
    }
    
    public static void get() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://baidu.com");

        // 注意1：底层HTTP连接还被response对象持有。这是为了可以直接使用这个链接socket来传输返回数据。
        // 所以在使用完毕后，**一定**要记得调用CloseableHttpResponse#close()方法。
        // 注意2：response的content必须要被完整的消费，否则可能无法被安全地重用。
        try (CloseableHttpResponse response = httpclient.execute(httpGet)){
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            String s = EntityUtils.toString(entity);
            System.out.println(s);

            EntityUtils.consume(entity);
        }

    }
    
    public static void post() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://targethost/login");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("username", "vip"));
        nvps.add(new BasicNameValuePair("password", "secret"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse response = httpclient.execute(httpPost);

        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
    }
    
    public static void responseHanlderTest() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://baidu.com");

        String result = httpClient.execute(httpGet, response -> {
            // 使用response...
            return "result";
        });
    }
}
