package enumtest;

import lombok.Data;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Converters;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * Created by mazhibin on 16/9/7
 */
@Data
@Converters({NodeType.Converter.class})
@Entity(value = "enum")
public class EnumEntity {
    @Id
    ObjectId id;

    String name;

    @Property("nodeType")
    NodeType nt;
}
