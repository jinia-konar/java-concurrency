package typesOfES;

import main.ExecutorServiceUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadedExecutorService extends ExecutorServiceUtil {
    @Override
    public void execute() {
        threadCreationWithSingleThreadPool();
    }

    /**
     * blocking queue: a queue with a fixed size that block the consumers and producers
     * the fixed thread pool ith size 1 is actually managed by a blocking queue
     * if a thread is killed because of some exception, it will be recreated
     */
    private void threadCreationWithSingleThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        runThreads(executorService);
    }
}
