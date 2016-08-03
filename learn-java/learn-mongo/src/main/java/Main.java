import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

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

        datastore.save(new User("fuck",100));

        List<User> userList = datastore.createQuery(User.class).disableValidation().field("age").lessThan(100).field("className").startsWith("U").asList();
        System.out.println(userList);
    }
}
