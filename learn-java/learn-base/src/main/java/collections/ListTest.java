package collections;

import java.util.*;

/**
 * Created by mazhibin on 16/7/3
 */
public class ListTest {

    static class A{
        int a;

        A(int a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return Integer.toString(a);
        }
    }

    public static void main(String[] args) {
        List<A> list1 = new ArrayList<A>();
        list1.add(new A(3));
        list1.add(new A(1));
        list1.add(new A(2));

        Collections.sort(list1, new Comparator<A>() {
            public int compare(A o1, A o2) {
                return (o1.a < o2.a) ? -1 : ((o1.a == o2.a) ? 0 : 1);
            }
        });

        System.out.println(list1);
    }
}
