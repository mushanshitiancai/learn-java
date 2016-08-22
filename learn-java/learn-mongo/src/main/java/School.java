import org.mongodb.morphia.annotations.Property;

/**
 * Created by mazhibin on 16/8/17
 */
public class School {

    @Property(value = "nn")
    private String name;
    private String address = "add default";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
