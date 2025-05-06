package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class OptimisticLocking {
    private static AtomicInteger count = new AtomicInteger(0);
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for(int i=0;i<4;i++) {
            executorService.execute(OptimisticLocking::increment);
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Count: " + count.get());
    }

    private static void increment() {
        int oldVal = count.get();
        int newVal = oldVal+1;
        if (!count.compareAndSet(oldVal, newVal)) {
            System.out.println("failed while incrementing");
        }
    }
}
