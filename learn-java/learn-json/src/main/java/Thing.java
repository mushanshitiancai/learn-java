import lombok.Data;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by mazhibin on 16/9/12
 */
@Data
abstract public class Thing {
    @Id
    private ObjectId id;
    private int      name;
    private Phone    phone;
}
