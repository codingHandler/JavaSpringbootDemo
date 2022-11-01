package com.ch.java8_Stream特性;

/**
 * @className: Student
 * @Auther: ch
 * @Date: 2022/5/2 18:54
 * @Description: TODO
 */

public class Student {
    private String name;
    private Integer age;
    private Integer type;

    public Student() {
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, Integer age, Integer type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
