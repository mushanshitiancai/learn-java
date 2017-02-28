package batchsizetest;

import com.mongodb.MongoClient;
import lombok.Data;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.query.MorphiaIterator;
import org.mongodb.morphia.query.Query;

import java.time.Duration;
import java.time.Instant;

/**
 * Created by mazhibin on 16/12/27
 */
public class Main {

    public static void main(String[] args) {
//        test();
//        test_asList();
        test_iter();
    }
    
    public static void test_asList(){
        MongoClient client = new MongoClient("localhost", 27017);
        Morphia morphia = new Morphia();

        Datastore datastore = morphia.createDatastore(client, "batch_size_test");
        Query<Item> query = datastore.createQuery(Item.class);

        System.out.println(query.getBatchSize());
        query.batchSize(5000);

        Instant start = Instant.now();
//        query.field("age").lessThan(31);
        System.out.println(query.asList());
        System.out.println(Duration.between(start,Instant.now()));
    }
    
    public static void test_iter(){
        MongoClient client = new MongoClient("localhost", 27017);
        Morphia morphia = new Morphia();

        Datastore datastore = morphia.createDatastore(client, "batch_size_test");
        Query<Item> query = datastore.createQuery(Item.class);
        
//        query.field("age").lessThan(31);
//        query.batchSize(2);

        Instant start = Instant.now();
        MorphiaIterator<Item, Item> fetch = query.fetch();
        try{
            for (Item item : fetch) {
                System.out.println(item);
                System.out.println(Duration.between(start,Instant.now()));
            }
        }finally {
            fetch.close();
        }
    }

    @Data
    @Entity(value = "item", noClassnameStored = true)
    public static class Item {
        private String name;
        private int age;

        Item() {

        }

        Item(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    private static void test() {
        MongoClient client = new MongoClient("localhost", 27017);
        Morphia morphia = new Morphia();

        Datastore datastore = morphia.createDatastore(client, "batch_size_test");

        for (int i = 0; i < 1000000; i++) {
            System.out.println(i);
            datastore.save(new Item("hello" + i, i));
        }
    }
}
