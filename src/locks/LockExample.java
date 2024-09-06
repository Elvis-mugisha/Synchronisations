package locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class LockExample {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void awaitSignal() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("Waiting for signal...");
            condition.await();
            System.out.println("Received signal!");
        } finally {
            lock.unlock();
        }
    }

    public void sendSignal() {
        lock.lock();
        try {
            System.out.println("Sending signal...");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockExample lockExample = new LockExample();
        new Thread(() -> {
            try {
                lockExample.awaitSignal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1000);
        new Thread(lockExample::sendSignal).start();
    }
}
