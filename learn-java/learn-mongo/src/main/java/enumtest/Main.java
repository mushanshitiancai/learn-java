package enumtest;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 * Created by mazhibin on 16/7/28
 */
public class Main {
    public static void main(String[] args) {
//        mongoDriverTest();
        morphiaTest();
    }

    private static void mongoDriverTest(){
        MongoClient client = new MongoClient("localhost",27017);
        MongoDatabase database = client.getDatabase("hello");

        FindIterable<Document> findIterable = database.getCollection("user").find();
        for (Document aFindIterable : findIterable) {
            System.out.println(aFindIterable);
        }
    }

    private static void morphiaTest(){
        MongoClient client = new MongoClient("localhost",27017);
        Morphia morphia = new Morphia();

//        morphia.mapPackage("");

        Datastore datastore = morphia.createDatastore(client,"hello");
        datastore.ensureIndexes();

//        EnumEntity e = new EnumEntity();
//        e.setName("3");
//        e.setNt(TypeBase.fromValue(NodeType.class,2));
//        datastore.save(e);

        System.out.println(datastore.createQuery(EnumEntity.class).asList());

        System.out.println(datastore.createQuery(EnumEntity.class).field("nodeType").equal(NodeType.Folder).asList());
    }
}
