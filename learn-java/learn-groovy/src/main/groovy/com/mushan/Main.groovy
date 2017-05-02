package com.mushan

/**
 * Created by mazhibin on 17/4/12
 */
class Main {
    static void main(String[] args) {
        def people = new People(name: "hello",age: 100)
        
        assert people.age == 100
        assert people.getProperty("age") == 100
    }
}

class People{
    String name
    int age
}