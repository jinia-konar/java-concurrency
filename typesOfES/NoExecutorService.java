package typesOfES;

import main.ExecutorServiceUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoExecutorService extends ExecutorServiceUtil {
    @Override
    public void execute() {
        threadCreationWithoutExService();
    }

    /**
     * 1 java thread == 1 OS thread
     */
    private void threadCreationWithoutExService() {
        for(int i=0;i<1000;i++) {
            Thread thread = new Thread(ExecutorServiceUtil::printThreadName); //Method reference instead of lambda
            thread.start();
        }
        printThreadName();
    }
}
