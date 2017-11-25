package web.bean;

import javax.validation.constraints.NotNull;

/**
 * Created by mazhibin on 2017/5/26 0026.
 */
public class User {
    @NotNull(message = "hehe name is null")
    private String name;
    private int age;

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
}
