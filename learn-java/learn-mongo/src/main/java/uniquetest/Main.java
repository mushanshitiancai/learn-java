package uniquetest;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 * Created by mazhibin on 16/11/1
 */
public class Main {
    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost",27017);
        Morphia morphia = new Morphia();
        morphia.mapPackage("uniquetest");

        Datastore datastore = morphia.createDatastore(client,"uniqueTest");
        datastore.ensureIndexes();

//        UniqueEntity entity = new UniqueEntity();
//        entity.setName("mzb");
//        datastore.save(entity);

    }
}
