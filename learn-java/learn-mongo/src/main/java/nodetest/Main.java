package nodetest;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * Created by mazhibin on 16/9/6
 */
public class Main {
    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost",27017);
        Morphia morphia = new Morphia();
//        morphia.mapPackage("");

        Datastore datastore = morphia.createDatastore(client,"hello");
        datastore.ensureIndexes();

        Query<AllNode> query = datastore.createQuery(AllNode.class);
        List<AllNode> nodes = query.field("name").startsWith("file").asList();
        System.out.println(nodes);

        Query<AllNode> query1 = datastore.createQuery(AllNode.class);
        List<AllNode> nodes2 = query1.field("name").startsWith("folder").asList();
        System.out.println(nodes2);

        AllNode node = new AllNode();
        node.setName("save");
        AllDetail detail = new AllDetail();
        detail.setPath("just path");
        node.setDetail(detail);
        datastore.save(node);

        //---

        System.out.println(datastore.createQuery(Node.class).field("name").startsWith("file").asList());
        System.out.println(datastore.createQuery(Node.class).field("name").startsWith("folder").asList());

        Node node1 = new Node();
        node1.setName("file11");
        FileDetail fileDetail = new FileDetail();
        fileDetail.setSize(1);
        node1.setFileDetail(fileDetail);
        datastore.save(node1);
    }
}
