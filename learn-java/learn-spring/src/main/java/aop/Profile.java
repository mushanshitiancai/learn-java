package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by mazhibin on 16/9/26
 */
@Aspect
@Component
public class Profile {

    @Pointcut("execution(* aop.a.A.play(..)) && args(name)")
    public void play(String name) {
    }

    @Before("play(name)")
    public void before(String name) {
        System.out.println("Before");
    }

    @Before("execution(* aop.a.A.play(..))")
    public void before3() {
        System.out.println("Before 3");
    }


    @Before("play(name)")
    public void before2(String name) {
        System.out.println("Before 2 " + name);
    }

//    @After("play(name)")
//    public void after(String name) {
//        System.out.println("After");
//    }
//
//    @AfterReturning("play(name)")
//    public void afterReturning(String name) {
//        System.out.println("AfterReturning");
//    }
//
//    @AfterThrowing("play(name)")
//    public void afterThrowing(String name) {
//        System.out.println("AfterThrowing");
//    }

    @Around("play(name)")
    public String around(ProceedingJoinPoint joinPoint, String name) {
        Signature signature = joinPoint.getSignature();
        if(signature instanceof MethodSignature){
            Method method = ((MethodSignature) signature).getMethod();
        }

        System.out.println(">> around "+name);
        try {
            System.out.println(joinPoint.getSignature().getName());
            System.out.println(Arrays.toString(joinPoint.getArgs()));
            System.out.println(joinPoint.getTarget());
            Object proceed = joinPoint.proceed();
            System.out.println("<< around");

            return proceed.toString();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return "";
    }
}
