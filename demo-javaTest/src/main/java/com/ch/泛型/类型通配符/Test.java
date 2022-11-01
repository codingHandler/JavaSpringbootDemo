package com.ch.泛型.类型通配符;

/**
 * @author: ch
 * @date: 2022/6/12 17:09
 * @description: 泛型统配符测试
 * 什么是类型统配符?
 * * 类型统配符一般是用 "?"代替具体的类型实参
 * * 所以,类型通配符是类型实参,而不是类型形参
 */
public class Test {
    public static void main(String[] args) {
        GenericWildcards<Number> numberGenericWildcards = new GenericWildcards<>();
        numberGenericWildcards.setValue(100);
        Test.showValue(numberGenericWildcards);

        System.out.println("---------------------------");
        GenericWildcards<Integer> integerGenericWildcards = new GenericWildcards<>();
        integerGenericWildcards.setValue(100);
        Test.showValue(integerGenericWildcards);
    }


    /**
     * @author ch
     * @date 2022/6/12 17:17
     * @description
     * 类型统配符上限:
     * *语法: 类/接口 <? extends 实参类型>
     *      要求该泛型的类型,只能是实参类型,或实参类型的子类类型
     **/
    public static void showValue(GenericWildcards<? extends Number> genericWildcards){
        Number value = genericWildcards.getValue();
        System.out.println(value);
    }
}
