package com.qezhhnjy.antq.common.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CompletableFuture功能介绍：https://juejin.cn/post/6970558076642394142
 *
 * @author zhaoyangfu
 * @since 2022/6/2-15:50
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // //可以自定义线程池
        // ExecutorService executor = Executors.newCachedThreadPool();
        // //runAsync的使用
        // CompletableFuture<Void> runFuture = CompletableFuture.runAsync(() -> System.out.println("run,关注公众号:捡田螺的小男孩"), executor);
        // //supplyAsync的使用
        // CompletableFuture<String> supplyFuture = CompletableFuture.supplyAsync(() -> {
        //     System.out.println("supply,关注公众号:捡田螺的小男孩");
        //     return "捡田螺的小男孩";
        // }, executor);
        // //runAsync的future没有返回值，输出null
        // System.out.println(runFuture.join());
        // //supplyAsync的future，有返回值
        // System.out.println(supplyFuture.join());
        // executor.shutdown(); // 线程池需要关闭
        //
        // CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
        //         ()->{
        //             System.out.println("原始CompletableFuture方法任务");
        //             return "捡田螺的小男孩";
        //         }
        // );
        //
        // CompletableFuture<Void> thenAcceptFuture = orgFuture.thenAccept((a) -> {
        //     if ("捡田螺的小男孩".equals(a)) System.out.println("关注了");
        //     else System.out.println("先考虑考虑");
        // });

        // CompletableFuture<String> a = CompletableFuture.supplyAsync(() -> {
        //     System.out.println("我执行完了");
        //     return "a";
        // });
        // CompletableFuture<String> b = CompletableFuture.supplyAsync(() -> {
        //     System.out.println("我也执行完了");
        //     return "b";
        // });
        // CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(a, b).whenComplete((m, k) -> System.out.println("finish"));

        CompletableFuture<String> f = CompletableFuture.completedFuture("第一个任务");
        //第二个异步任务
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> "第二个任务", executor)
                .thenComposeAsync(data -> {
                    System.out.println("thenComposeAsync "+data); return f; //使用第一个任务作为返回
                }, executor);
        System.out.println(future.join());
        executor.shutdown();
    }
}
