package typesOfES;

import main.ExecutorServiceUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedExecutorService extends ExecutorServiceUtil {
    @Override
    public void execute() {
        threadCreationWithFixedThreadPool();
        //threadCreationWithFixedAvailableProcessesThreadPool();
    }

    /**
     * blocking queue: a queue with a fixed size that block the consumers and producers
     * the fixed thread pool is actually managed by a blocking queue
     */
    private void threadCreationWithFixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        runThreads(executorService);
    }

    /**
     * the pool size has to be equal to core/cpu size when task is CPU intensive
     * but when task is IO intensive then we can have larger pool size so that threads at waiting state can be used
     * by the other tasks and utilize the CPU/internal resources
     */
    private void threadCreationWithFixedAvailableProcessesThreadPool() {
        int coreCount = Runtime.getRuntime().availableProcessors();
        System.out.println("core count: " + coreCount);
        ExecutorService executorService = Executors.newFixedThreadPool(coreCount);
        runThreads(executorService);
    }
}
