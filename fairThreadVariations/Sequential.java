package fairThreadVariations;

import main.ThreadDataTypes;

import java.time.Duration;
import java.time.LocalDateTime;

public class Sequential extends ThreadDataTypes {
    private int maxValue = 10000000;
    private int totalPrimeNumbers = 0;

    private void checkPrime(int x) {
        if(x == 1) return;

        for(int i=2;i<=(int)Math.sqrt(x);i++) {
            if(x%i == 0) return;
        }

        totalPrimeNumbers++;
    }

    @Override
    public void printPrimeNumbers() {
        LocalDateTime start = LocalDateTime.now();

        for(int i=2;i<=maxValue;i++) {
            checkPrime(i);
        }

        System.out.println("checking till " + maxValue + " found " + totalPrimeNumbers + " prime numbers in time: " +
                Duration.between(start, LocalDateTime.now()).toMillis());
    }
}
