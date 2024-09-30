package typesOfES;

import main.ExecutorServiceUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedExecutorService extends ExecutorServiceUtil {
    @Override
    public void execute() {
        threadCreationWithCachedThreadPool();
    }

    /**
     * Synchronous queue: a queue with a single size
     * in cached thread pool, when a task is sent to the queue then it will check if any
     * existing thread available then assign to it, if not then create a new thread and assign the task to it.
     * Life cycle: if any existing thread is waiting for more than 60 sec then it will kill the thread.
     */
    private void threadCreationWithCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        runThreads(executorService);
    }
}
