package reftest;

import com.mongodb.MongoClient;
import lombok.Data;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.query.Query;

/**
 * Created by mazhibin on 12/29/16
 */
public class Main {

    public static void main(String[] args) {
//        test();
        test2();
    }
    
    public static void test2(){
        MongoClient client = new MongoClient("localhost", 27017);
        Morphia morphia = new Morphia();

        Datastore datastore = morphia.createDatastore(client, "ref_test");
        Query<Item> query = datastore.createQuery(Item.class);
        query.field("age").equal(10);

        System.out.println(query.asList());
    }

    @Data
    @Entity(value = "Student", noClassnameStored = true)
    public static class Item {
        @Id
        private ObjectId id;
        private String name;
        private int age;
        @Reference
        private AClass aClass;

        Item() {

        }

        Item(String name, int age, AClass aClass) {
            this.name = name;
            this.age = age;
            this.aClass = aClass;
        }
    }

    @Data
    @Entity(value = "Class", noClassnameStored = true)
    public static class AClass {
        @Id
        private ObjectId id;
        private String name;
        private int count;

        AClass() {

        }

        AClass(String name, int count) {
            this.name = name;
            this.count = count;
        }
    }

    private static void test() {
        MongoClient client = new MongoClient("localhost", 27017);
        Morphia morphia = new Morphia();

        Datastore datastore = morphia.createDatastore(client, "ref_test");

        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            AClass aClass = new AClass("english", i);
            datastore.save(aClass);
            datastore.save(new Item("hello" + i, i, aClass));
        }
    }
}
