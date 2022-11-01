package com.ch;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @className: Test
 * @Auther: ch
 * @Date: 2022/5/9 13:10
 * @Description: TODO
 */
@Slf4j
public class Test {
    private static RateLimiter rateLimiter = RateLimiter.create(5);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService =
                new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>());

        Callable<String> callableTask = () -> {
            TimeUnit.MILLISECONDS.sleep(300);
            return "Task's execution";
        };

        List<Callable<String>> callableTasks = new ArrayList<>();
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);

        //Future<String> future = executorService.submit(callableTask);
        List<Future<String>> futures = executorService.invokeAll(callableTasks);
        futures.forEach(item->{
            try {
                System.out.println(item.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();





        /*for (int index = 0; index < 100; index++) {
         *//*if (rateLimiter.tryAcquire(190, TimeUnit.MILLISECONDS)) {
                handle(index);
            }*//*

                rateLimiter.acquire();
                handle(index);

        }*/

        int[] ints = {1, 2};
       /* arrMethod(ints);
        show(ints);*/
    }

    private static int digui(int i) {
        if (i == 1) {
            return 1;
        }

        return i + digui(--i);
    }


    private static void show(int[] arr) {
        for (int i : arr) {
            System.out.println(i + "");
        }
    }

    private static int arrMethod(int[] arr) {
        return arr[0];
    }

    private static void handle(int i) {
        log.info("{}", i);
    }


}
