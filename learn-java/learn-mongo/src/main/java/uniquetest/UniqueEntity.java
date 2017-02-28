package uniquetest;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

/**
 * Created by mazhibin on 16/11/1
 */
@Entity
@Indexes({
    @Index(fields = {@Field("name")},options = @IndexOptions(unique = true,dropDups = true))
})
public class UniqueEntity {
    @Id
    ObjectId id;

    @Property
    String name;
}
