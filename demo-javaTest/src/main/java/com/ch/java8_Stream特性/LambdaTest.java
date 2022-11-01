package com.ch.java8_Stream特性;


import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author: ch
 * @date: 2022/9/8 14:13
 * @description: 函数式接口
 * <p>
 * Consumer:消费型借口	T	void	对类型T的对象进行操作，包含方法：void accept（T t）
 * Supplier:供给型借口	无	T	返回类型为T的对象，包含方法：T get（）
 * Function<T,R>函数型接口	T	R	对类型T的对象进行操作，返回结果为R的对象，包含方法：R apply（T t）
 * Predicate:断定型接口	T	boolean	确定类型T的对象是否满足某约束，并返回boolean值。包含方法：boolean test（T t）
 */
public class LambdaTest {
    public static void main(String[] args) {
        // 消费型接口,有形参没有返回值
        consumerTest(100D, x -> System.out.println("买东西花了" + x + "块钱"));
        // 供给形接口,没有形参,有返回值
        String str = supplierTest(() -> "返回了东西");
        System.out.println("supplier 供给型接口返回值: " + str);
        // 函数型接口,有形参有返回值
        List<String> list = Arrays.asList("123", "xyzc", "07890");
        List<Integer> integers = functionTest(list, x -> x.length());
        System.out.println(integers);
        // Predicate
        List<String> cityList = Arrays.asList("北京", "上海", "深圳", "南京", "杭州");
        List<String> cityNewList = predicateTest(cityList, x -> x.contains("北") || x.contains("南"));
        System.out.println(cityNewList);
    }

    private static List<String> predicateTest(List<String> list, Predicate<String> predicate) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                arrayList.add(s);
            }
        }
        return arrayList;
    }

    private static List<Integer> functionTest(List<String> list, Function<String, Integer> function) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (String s : list) {
            Integer length = function.apply(s);
            arrayList.add(length);
        }
        return arrayList;
    }

    private static String supplierTest(Supplier<String> supplier) {
        return supplier.get();
    }


    private static void consumerTest(Double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }
}
