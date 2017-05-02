package com.mushan

/**
 * Created by mazhibin on 17/4/20
 */
class GClass  {
    def func() {
        println "call_func"
    }

//    def invokeMethod(String name, Object args) {
//        if (name == "println") 
//            this.metaClass.invokeMethod(name, args);
//        else
//            println "invokeMethod $name $args"
//    }
//
//    def methodMissing(String name, def args) {
//        println "methodMissing $name $args"
//    }

    public static void main(String[] args) {
        def c = new GClass()
        c.func()
        c.func1(1)
    }
}


