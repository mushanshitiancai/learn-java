package lombok;

/**
 * Created by mazhibin on 16/10/14
 */
@Data
@ToString(callSuper = true)
public class Child extends Parent {
    String childName;
}
