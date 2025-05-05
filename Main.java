import main.ThreadDataTypes;
import fairThreadVariations.AtomicDataTypesFair;

public class Main {
    public static void main(String[] args) {
        /*ExecutorServiceUtil executorService = new FixedExecutorService();
        executorService.execute();*/

        //Concurrency in Depth: https://www.youtube.com/watch?v=2PjlaUnrAMQ&list=PLsdq-3Z1EPT3VjDhjMb5yBsgn0wn2-fjp
        ThreadDataTypes threadDataTypes = new AtomicDataTypesFair();
        threadDataTypes.printPrimeNumbers(); //665110

    }
}
