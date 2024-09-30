package typesOfES;

import main.ExecutorServiceUtil;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorService extends ExecutorServiceUtil {

    @Override
    public void execute() {
        //threadCreationWithSingleSchedule();
        //threadCreationWithFixedRateSchedule();
        threadCreationWithFixedDelaySchedule();
    }

    /**
     * Delay queue: a preemptive queue, schedule a task on the basis of the delay
     * more threads are created if required
     */
    private void threadCreationWithSingleSchedule() {
        java.util.concurrent.ScheduledExecutorService executorService =
                Executors.newScheduledThreadPool(10);
        System.out.println("Thread execution started at: " + LocalDateTime.now());
        executorService.schedule(ExecutorServiceUtil::printThreadName, 15, TimeUnit.SECONDS);
    }

    /**
     * There is no stoppage to it, it will execute till the application is up,
     * The second execution will wait to go into the delay queue until the delay period,
     * but will execute one after another
     */
    private void threadCreationWithFixedRateSchedule() {
        java.util.concurrent.ScheduledExecutorService executorService =
                Executors.newScheduledThreadPool(10);
        System.out.println("Thread execution started at: " + LocalDateTime.now());
        executorService.scheduleAtFixedRate(() -> {printThreadNameWithDelay(12000);},
                15, 10, TimeUnit.SECONDS);
    }

    /**
     * There is no stoppage to it, it will execute till the application is up,
     * The second execution will be sent to the delay queue with the delay period
     * after the first execution in completed
     */
    private void threadCreationWithFixedDelaySchedule() {
        java.util.concurrent.ScheduledExecutorService executorService =
                Executors.newScheduledThreadPool(10);
        System.out.println("Thread execution started at: " + LocalDateTime.now());
        executorService.scheduleWithFixedDelay(() -> {printThreadNameWithDelay(12000);},
                15, 10, TimeUnit.SECONDS);
    }
}
