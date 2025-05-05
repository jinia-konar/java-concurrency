package fairThreadVariations;

import main.ThreadDataTypes;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Use Case	Atomic Alternative
 * Counting page views	AtomicInteger/AtomicLong
 * Flag for stop condition	AtomicBoolean
 * Concurrent reference swap	AtomicReference
 * Non-blocking counters/maps	Used inside ConcurrentHashMap
 */
public class AtomicDataTypes extends ThreadDataTypes {
    public static final int threadPoolSize = 10;
    private int maxValue = 10000000;
    private AtomicInteger totalPrimeNumbers = new AtomicInteger(0);

    private boolean checkPrime(int x) {
        if(x == 1) return false;

        for(int i=2;i<=(int)Math.sqrt(x);i++) {
            if(x%i == 0) return false;
        }
        return true;
    }

    private Runnable job(int start, int end) {
        return () -> {
            LocalDateTime startTime = LocalDateTime.now();
            int localCount = 0;
            for (int i = start; i <= end; i++) {
                if (checkPrime(i)) localCount++;
            }
            totalPrimeNumbers.addAndGet(localCount);
            System.out.println("Thread [" + start + " - " + end + "] found " + localCount + " primes in time: " +
                    Duration.between(startTime, LocalDateTime.now()).toMillis());
        };
    }

    /**
     * This is un-fare way of doing thread safe because every thread is taking different time to execute its job
     * because the job with latter threads are again starting from the start to check divisibility
     */
    @Override
    public void printPrimeNumbers() {
        LocalDateTime start = LocalDateTime.now();
        int batches = maxValue / threadPoolSize;
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
        int startVal = 2;

        for(int i=0;i<threadPoolSize;i++) {
            int endVal = i == threadPoolSize-1 ? maxValue : startVal + batches - 1;
            executorService.execute(job(startVal, endVal));
            startVal = endVal + 1;
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("checking till " + maxValue + " found " + totalPrimeNumbers.get() + " prime numbers in time: " +
                Duration.between(start, LocalDateTime.now()).toMillis());
    }
}
