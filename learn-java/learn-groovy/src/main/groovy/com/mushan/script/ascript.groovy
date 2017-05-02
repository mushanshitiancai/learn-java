package com.mushan.script

import groovy.time.TimeCategory

class A {
    def func() {
        println "call_func"

        use(TimeCategory) {
            def a = {
                println 1.minute.from.now
                println 10.hours.ago

                def someDate = new Date()
                println someDate - 3.months
            }()
        }
    }

    def invokeMethod(String name, Object args) {
        println "invokeMethod $name $args"
    }


    def methodMissing(String name, def args) {
        println "methodMissing $name $args"
    }

    def getProp() {
        println("getProp")

        return "getProp"
    }

    def propertyMissing(String name) {
        println("propertyMissing $name")
    }

    def func2(){
        println("func__2")
    }

}

def a = new A()
a.func()
a.func(1)
a.func2()

a.prop

a.metaClass.func2 = {
    println("func_2")
}
//a.metaClass.func2 << {
//    println("func_2_2")
//}
a.func2()

