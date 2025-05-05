package main;

import java.time.Duration;
import java.time.LocalDateTime;

public abstract class ThreadDataTypes {

    /**
     * DataType: Time Taken
     * Sequential: 3684 ms
     * Atomic Un-fair: 786 ms
     * Atomic Fair: 697 ms
     */
    abstract public void printPrimeNumbers();
}
