package main;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentQueue {

    /**
     * Java provides
     * ArrayBlockingQueue
     * LinkedBlockingQueue
     * PriorityBlockingQueue
     * DelayQueue
     * SynchronousQueue
     * LinkedTransferQueue
     * @param args
     */
    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for(int i=0;i<100000;i++) {
            int x = i;
            executorService.execute(() -> {
                queue.enqueue(x);
            });
        }

        for(int i=0;i<100000;i++) {
            executorService.execute(() -> {
                queue.dequeue();
            });
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Queue size: " + queue.getQueue().size());
    }
}

class MyQueue {
    private List<Integer> queue = new LinkedList<>();
    private final Object lock = new Object();

    public List<Integer> getQueue() {
        return queue;
    }

    public void enqueue(int x) {
        synchronized (lock) {
            queue.add(x);
        }
    }

    public void dequeue() {
        synchronized (lock) {
            if (queue.size() <= 0) {
                throw new RuntimeException("Can not dequeue from an empty queue");
            }
            int val = queue.removeFirst();
            System.out.println("Dequeue: " + val);
        }
    }
}
