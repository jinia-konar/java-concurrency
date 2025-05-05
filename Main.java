import main.ThreadDataTypes;
import fairThreadVariations.AtomicDataTypesFair;

public class Main {
    public static void main(String[] args) {
        /*ExecutorServiceUtil executorService = new FixedExecutorService();
        executorService.execute();*/

        ThreadDataTypes threadDataTypes = new AtomicDataTypesFair();
        threadDataTypes.printPrimeNumbers(); //665110

    }
}
