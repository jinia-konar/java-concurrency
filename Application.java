import main.ExecutorServiceUtil;
import typesOfES.CachedExecutorService;
import typesOfES.ScheduledExecutorService;
import typesOfES.SingleThreadedExecutorService;

public class Application {
    public static void main(String[] args) {
        ExecutorServiceUtil executorService = new SingleThreadedExecutorService();
        executorService.execute();
    }
}
