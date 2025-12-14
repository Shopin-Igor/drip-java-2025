package model;

import annotations.Min;
import annotations.Max;
import annotations.NotNull;

public class MyClass {
    @NotNull
    @Min(10)
    @Max(99)
    private Integer age;

    @NotNull
    private String name;

    public MyClass(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public static MyClass create() {
        return new MyClass(42, "FactoryCreated");
    }

    @Override
    public String toString() {
        return "MyClass{age=" + age + ", name='" + name + "'}";
    }
}
