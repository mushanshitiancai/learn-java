package annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

/**
 * Created by mazhibin on 17/2/15
 */
public class AnnotationTest {
    
    @Retention(RetentionPolicy.RUNTIME)
    @interface A{
        String value();
    }

    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @interface AI{
        String value();
    }

    @A("Parent-A")
    @AI("Parent-AI")
    class Parent{
        @A("Parent-parentMethod-A")
        @AI("Parent-parentMethod-AI")
        public void parentMethod(){
            
        }

        @A("Parent-parentMethod_BeOverride-A")
        @AI("Parent-parentMethod_BeOverride-AI")
        public void parentMethod_BeOverride(){
            
        }
    }

    @A("ChildInterface-A")
    @AI("ChildInterfaceAI")
    interface ChildInterface{
        @A("ChildInterface-childMethod-A")
        @AI("ChildInterface-childMethod-AI")
        void childMethod();
    }
    
    class Child extends Parent implements ChildInterface{

        @Override
        public void childMethod() {
        }
    }
    
    AnnotationTest(){
        Child child = new Child();

        System.out.println("== class getAnnotations");
        Annotation[] annotations1 = child.getClass().getAnnotations();
        for (Annotation annotation : annotations1) {
            System.out.println(annotation);
        }

        System.out.println("== class getDeclaredAnnotations");
        Annotation[] declaredAnnotations = child.getClass().getDeclaredAnnotations();
        for (Annotation annotation : declaredAnnotations) {
            System.out.println(annotation);
        }

        System.out.println("== declaredMethods");

        Method[] declaredMethods = child.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            Annotation[] annotations = declaredMethod.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
            }
        }

        System.out.println("==methods");

        Method[] methods = child.getClass().getMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
            }
        }
    }
    
    
    public static void main(String[] args) {
        new AnnotationTest();
    }
}
