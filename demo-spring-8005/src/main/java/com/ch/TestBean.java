package com.ch;


import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: ch
 * @date: 2022/9/17 13:36
 * @description: TODO
 */
public class TestBean {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        applicationContext.getBean("bean1");
        Bean1 bean1 = applicationContext.getBean("bean1", Bean1.class);
        bean1.getAge();

    }
}

class Bean1 {
    private String name;
    private Integer age;

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
}
