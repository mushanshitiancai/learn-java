package startwithtest;

import com.mongodb.MongoClient;
import lombok.Data;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by mazhibin on 16/12/23
 */
public class Main {
    
    @Data
    public static class Item{
        private String name;
        private String desc;
        
        Item(){
            
        }
        
        Item(String name,String desc){
            this.name = name;
            this.desc = desc;
        }
    }
    
    public static void main(String[] args) {
        test();
    }

    private static void test(){
        MongoClient client = new MongoClient("localhost",27017);
        Morphia morphia = new Morphia();

        Datastore datastore = morphia.createDatastore(client, "start_with_test");
        
//        datastore.save(new Item("hello","a (b) c"));

        Query<Item> query = datastore.createQuery(Item.class);
        List<Item> result = query.asList();
        System.out.println(result);

        query = datastore.createQuery(Item.class);
//        query.field("desc").startsWith("a (");
        query.field("desc").equal(Pattern.compile("^a \\("));
        System.out.println(query.asList());
    }
}
