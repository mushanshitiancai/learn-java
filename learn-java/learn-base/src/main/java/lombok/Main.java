package lombok;

/**
 * Created by mazhibin on 16/10/14
 */
public class Main {
    public static void main(String[] args) {
        Child child = new Child();
        child.setChildName("child");
        child.setParentName("parent");

        System.out.println(child);
    }
}
