package com.banyue.demo;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description 测试线程池之间，关于父线程与线程池中子线程值之间的传递
 * @Author zhangsip
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2024/10/16
 */
public class ThreadLocalDemo {


    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    private static TransmittableThreadLocal<String> transmittableThreadLocal = new TransmittableThreadLocal<String>();

    public static void main(String[] args) {

//        testThreadLocal();
//        testInheritableThreadLocal2();
        testTransmittableThreadLocal();

    }

    private static void testThreadLocal(){

        threadLocal.set("hello");

        System.out.println(threadLocal.get());

        new Thread(() -> {
            System.out.println(threadLocal.get());
        }).start();
    }


    public static void testInheritableThreadLocal(){

        inheritableThreadLocal.set("hello inheritableThreadLocal");

        System.out.println(inheritableThreadLocal.get());

        new Thread(() -> {
            System.out.println(inheritableThreadLocal.get());
        }).start();

    }

    public static void testInheritableThreadLocal2(){

        inheritableThreadLocal.set("hello inheritableThreadLocal");

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        executorService.submit(()-> System.out.println(inheritableThreadLocal.get()));

        // 测试：修改父线程值，线程池中的线程是否能获取到更新的值
        inheritableThreadLocal.set("hello inheritableThreadLocal2");
        executorService.submit(()-> System.out.println(inheritableThreadLocal.get()));
        // 测试结果是：无法获取到最新的值。
    }

    private static void testTransmittableThreadLocal(){
        // 测试阿里的ttl 框架

        // 1.先设置主线程的值
        transmittableThreadLocal.set("hello transmittableThreadLocal");

        ExecutorService threadPool = Executors.newFixedThreadPool(1);

        Executor executor = TtlExecutors.getTtlExecutor(threadPool);

        // 2.线程池提交任务，线程池中的子线程获取线程中的值
        executor.execute(()-> System.out.println(transmittableThreadLocal.get()));

        // 修改主线程中的值
        transmittableThreadLocal.set("hello");

        // 线程池中的线程再次获取变更后的值
        executor.execute(()-> System.out.println(transmittableThreadLocal.get()));
        // 测试结果：可以获取到最新的值。
    }




}
