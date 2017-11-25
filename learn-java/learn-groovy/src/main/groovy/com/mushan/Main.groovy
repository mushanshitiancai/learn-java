package com.mushan

/**
 * Created by mazhibin on 17/4/12
 */
class Main {
    static void main(String[] args) {
        def people = new People(name: "hello",age: 100)
        
        assert people.age == 100
        assert people.getProperty("age") == 100
        
        people.metaClass.fun = {println "funx"}
        
        people.fun()
        people.callFun()
        
        "hello--".printx()
    }
}

class People{
    String name
    int age
    
    def fun(){
        println "fun"
    }
    
    def callFun(){
        fun()
    }
}