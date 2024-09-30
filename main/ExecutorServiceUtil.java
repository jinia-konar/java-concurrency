package main;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class ExecutorServiceUtil {
    //https://www.youtube.com/playlist?list=PLhfHPmPYPPRl0LntrCBnQD5ln6lnqqoms
    public abstract void execute();

    protected static void runThreads(ExecutorService executorService) {
        for(int i=0;i<100;i++) {
            executorService.execute(ExecutorServiceUtil::printThreadName);
        }
        //even though above loop is written above don't expect next line to execute synchronously, it depends
        // when thread pool size is equivalent to number of tasks then it is a sync process
        // but when number of tasks are greater than thread pool size then it is async i.e
        // based on the availability of the threads
        printThreadName();
    }

    protected static void printThreadName() {
        System.out.println("Thread name: " + Thread.currentThread().getName() +
                " time: " + LocalDateTime.now());
    }

    protected static void printThreadNameWithDelay(long milliSecond) {
        Thread thread = Thread.currentThread();
        System.out.println("Started Thread name: " + thread.getName() +
                " time: " + LocalDateTime.now());
        try{
            Thread.sleep(milliSecond);
        } catch (InterruptedException e) {
            System.out.println("Unable to add delay while printing");
        }
        System.out.println("Finished Thread name: " + thread.getName() +
                " time: " + LocalDateTime.now());
    }
}
