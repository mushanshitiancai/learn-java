package basic;

/**
 * Created by mazhibin on 16/9/20
 */
public class ConstructorTest {
}
class Parent{
    int a;

    Parent(int a){
        this.a = a;
    }
}

class Child extends Parent{
    int b;

    Child(int a){
        super(a);
        this.b = b;
    }

    Child(int a,int b){
        this(a+b);
    }
}