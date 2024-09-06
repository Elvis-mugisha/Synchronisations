package deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockPrevention {
    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();

    public void method1() {
        try {
            if (lock1.tryLock()) {
                System.out.println("Lock1 acquired, trying for Lock2...");
                if (lock2.tryLock()) {
                    System.out.println("Lock2 acquired.");
                    // critical section
                }
            }
        } finally {
            lock1.unlock();
            lock2.unlock();
        }
    }

    public void method2() {
        try {
            if (lock2.tryLock()) {
                System.out.println("Lock2 acquired, trying for Lock1...");
                if (lock1.tryLock()) {
                    System.out.println("Lock1 acquired.");
                    // critical section
                }
            }
        } finally {
            lock2.unlock();
            lock1.unlock();
        }
    }

    public static void main(String[] args) {
        DeadlockPrevention deadlockPrevention = new DeadlockPrevention();
        new Thread(deadlockPrevention::method1).start();
        new Thread(deadlockPrevention::method2).start();
    }
}
