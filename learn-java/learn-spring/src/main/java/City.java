/**
 * Created by mazhibin on 16/7/26
 */
public class City {
    private String name;
    private String state;

    public City() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return name + "{" + state + "}";
    }
}
