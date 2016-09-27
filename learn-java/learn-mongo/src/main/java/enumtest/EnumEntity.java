package enumtest;

import lombok.Data;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

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

    @Embedded("in")
    In in;

    @Property("nodeType")
    NodeType nt;
}
