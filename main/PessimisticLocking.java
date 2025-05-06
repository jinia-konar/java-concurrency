package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PessimisticLocking {

    public static void main(String[] args) {
        Util util = new Util();
        util.run();
    }
}

class Util {
    int count = 0;
    private final Object lock = new Object();
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for(int i=0;i<100000;i++) {
            executorService.execute(() -> {
                synchronized (lock) {
                    count++;
                }
            });
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(count);
    }
}
