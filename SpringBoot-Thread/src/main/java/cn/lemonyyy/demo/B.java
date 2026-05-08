package cn.lemonyyy.demo;

import java.util.ArrayList;
import java.util.concurrent.*;

public class B {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(15);
        //计数等待 非必要存在 下放存在异步阻塞的Future的get方法 (Waits if necessary for the computation to complete, and then retrieves its result)
        CountDownLatch latch = new CountDownLatch(1);
        ArrayList<Future<?>> futures = new ArrayList<>();

        try {
            new A(B::test);
//            // 用这种方式执行线程，异常会被吞掉
//            Future<?> future = executorService.submit(() -> {
//                new A(B::test);
//                latch.countDown(); // 计数器减一
//            });
//            futures.add(future);
//            // 异步等待所有任务完成
//            latch.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //关闭线程池
            executorService.shutdown();
        }

        //查看循环过程是否有异常抛出 若存在异常 则自动抛出
        for (Future<?> future : futures) {
            future.get();
        }
    }

    public static Double test(double[] aa) {
        return null;
    }
}
