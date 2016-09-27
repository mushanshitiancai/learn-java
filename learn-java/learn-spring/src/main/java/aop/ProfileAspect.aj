package aop;

/**
 * Created by mazhibin on 16/9/27
 */
public aspect ProfileAspect {
    pointcut play(): execution(* play(..));

//    after() returning(): play() {
//        System.out.println("aspect after returning");
//    }
//
//    after(): play(){
//        System.out.println("aspect after");
//    }
}
