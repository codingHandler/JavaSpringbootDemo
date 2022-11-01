package com.ch.泛型.泛型方法;

import java.util.List;
import java.util.Random;

/**
 * @author: ch
 * @date: 2022/6/12 16:29
 * @description: 泛型方法
 * 注意:
 * * 泛型方法和泛型类没有任务关系
 * * 泛型方法能使方法独立于类而产生变化
 * * 如果static方法要使用泛型能力,就必须使其成为泛型方法
 */
public class GenericMethod {

    /**
     * @param <E> 泛型标识,具体类型由调用方法的时候来指定
     * @author ch
     * @date 2022/6/12 16:41
     * @description 泛型方法
     **/
    public <E> E getValue(List<E> list) {
        return list.get(new Random().nextInt(list.size()));
    }
}
