import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

/**
 * Created by mazhibin on 17/3/1
 */
public class Main {
    public static void main(String[] args) throws UnknownHostException, ExecutionException, InterruptedException {
        TransportClient client;
        client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

        GetResponse getFields = client.prepareGet("test", "employee", "1").get();
        System.out.println(getFields);

        SearchResponse searchResponse = client.search(new SearchRequest()).get();
        System.out.println(searchResponse.getHits().getTotalHits());
        System.out.println(searchResponse.getHits().getHits()[0].getSourceAsString());

        client.close();
    }
}
