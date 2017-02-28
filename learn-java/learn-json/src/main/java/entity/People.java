package entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by mazhibin on 16/11/14
 */
public class People {

    @JSONField(name = "Name")
    private String name;
    @JSONField(name = "Age")
    private int age;

    People(){
    }

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "People{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}
