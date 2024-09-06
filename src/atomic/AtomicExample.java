package atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {
    private final AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        int newCount = count.incrementAndGet();
        System.out.println("Atomic count incremented to: " + newCount);
    }

    public int getCount() {
        return count.get();
    }

    public static void main(String[] args) {
        AtomicExample atomicExample = new AtomicExample();
        atomicExample.increment();
        atomicExample.increment();
        System.out.println("Final atomic count: " + atomicExample.getCount());
    }
}
