package synchronizedmethods;

public class Counter {
    private int count = 0;

    // Synchronized method
    public synchronized void increment() {
        count++;
        System.out.println("Incremented count to: " + count);
    }

    // Synchronized block
    public void incrementWithBlock() {
        synchronized (this) {
            count++;
            System.out.println("Incremented with block to: " + count);
        }
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        counter.increment();
        counter.incrementWithBlock();
        System.out.println("Final count: " + counter.getCount());
    }
}
