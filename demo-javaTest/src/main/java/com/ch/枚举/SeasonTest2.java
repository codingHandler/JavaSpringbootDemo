package com.ch.枚举;

/**
 * @className: SeasonTest2
 * @Auther: ch
 * @Date: 2022/5/2 21:20
 * @Description: 使用enum关键字来定义枚举类
 * 说明:定义枚举类默认继承与java.lang.Enum类
 * <p>
 * Enum类中的常用方法:
 * values():返回枚举类型的对象数组,该方法可以很方便的遍历所有的枚举值
 * valueOf(String str):可以把一个字符串转为对应的枚举类对象,要求字符串必须是枚举类对象名一致
 * toString():返回当前枚举类对象的常量的名称
 */
public class SeasonTest2 {
    public static void main(String[] args) {
        Season1 spring = Season1.SPRING;
        spring.show();
        Season1.AUTUMN.show();
        System.out.println(spring);
        System.out.println(spring.getClass().getSuperclass());

        Season1[] values = Season1.values();
        for (Season1 value : values) {
            System.out.println(value);
        }
    }
}

interface SeasonInfo{
    void show();
}

// 使用enum关键字定义枚举类
enum Season1 implements SeasonInfo{

    // 3,提供当前枚举类的多个对象,多个对象之间用","隔开,末尾对象";"结束
    SPRING("春天", "春暖花开"){
        @Override
        public void show() {
            System.out.println("春天");
        }
    },
    SUMMER("夏天", "夏日炎炎"){
        @Override
        public void show() {
            System.out.println("夏天");
        }
    },
    AUTUMN("秋天", "秋高气爽"){
        @Override
        public void show() {
            System.out.println("秋天");
        }
    },
    WINTER("冬天", "冰天雪地"){
        @Override
        public void show() {
            System.out.println("冬天");
        }
    };


    // 1,申明season属性
    private final String seasonName;

    private final String seasonDesc;

    // 2,私有化构造器,并给属性对象赋值
    private Season1(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }
}