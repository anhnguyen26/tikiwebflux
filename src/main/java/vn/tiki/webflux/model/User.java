package vn.tiki.webflux.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private String id;

    private String name;
    private int age;
    private double salary;

    public User() {
    }

    public User(String id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
}
