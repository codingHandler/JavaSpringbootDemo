package com.ch.自定义实现分布式锁;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: ch
 * @date: 2022/8/16 14:11
 * @description: 自定义实现redis分布式锁
 */
public class RedisDistributedLock {

    private StringRedisTemplate stringRedisTemplate;
    // 锁前缀
    private static final String LOCK_PREFIX = "lock:";

    private static final String ID_PREFIX = UUID.randomUUID().toString().replace("-", "") + "threadID:";

    public RedisDistributedLock(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /***
     * 尝试获取锁
     * 加锁过程必须设置过期时间
     * 如果没有设置过期时间,手动释放锁的操作出现问题,就会发生死锁,锁永远得不到释放
     * 加锁和设置过期时间的过程必须是原子操作
     * 如果加锁后服务宕机或程序泵坤,来不及设置过期时间,同样会发生死锁
     * @param name
     * @param expireTime
     * @return
     */
    public Boolean tryLock(String name, long expireTime) {
        // value 当前线程的线程id
        String threadId = ID_PREFIX + Thread.currentThread().getId();
        // setIfAbsent()方法,当key不存在的时候,设置并返回true,当存在的时候,设置失败返回false
        Boolean res = stringRedisTemplate.opsForValue()
                .setIfAbsent(LOCK_PREFIX + name, threadId, expireTime, TimeUnit.SECONDS);
        return res;
    }

    /***
     * 加锁,有阻塞
     * @param name 锁名称
     * @param expireTime 锁过期时间
     * @param requestTimeout 请求超时时间
     * @return
     */
    public Boolean lock(String name, long expireTime, long requestTimeout) {
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        Boolean res = false;
        do {
            // 尝试获取锁
            res = tryLock(LOCK_PREFIX + name, expireTime);
            // 获取锁失败
            if (!res) {
                // 判断是否超过请求时间,超过则
                if ((System.currentTimeMillis() - startTime) > (requestTimeout - 50)) {
                    break;
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (!res);
        return res;
    }

    /***
     * 解锁:
     * 1,在获取锁时存入线程标识,可以使用UUID表示,因为如果单独是线程池线程id是递增的,多个服务可能有多个线程池,导致线程id一致
     * 2,在释放锁时先获取锁中的线程标识,判断是否与当前线程标识一致,如果一致则可以删除释放锁,如果不一致则不可以删除释放锁
     * @param name 锁名称
     * @return
     */
    public Boolean unlock(String name) {
        // 线程id
        String threadId = ID_PREFIX + Thread.currentThread().getId();
        // 当前获得锁的线程id
        String id = stringRedisTemplate.opsForValue().get(LOCK_PREFIX + name);

        if (threadId.equals(id)) {
            return stringRedisTemplate.delete(LOCK_PREFIX + name);
        }
        return false;
    }


}
