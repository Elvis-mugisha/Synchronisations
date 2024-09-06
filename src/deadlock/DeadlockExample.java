package deadlock;

public class DeadlockExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void method1() {
        synchronized (lock1) {
            System.out.println("Lock1 acquired, waiting for Lock2...");
            synchronized (lock2) {
                System.out.println("Lock2 acquired.");
            }
        }
    }

    public void method2() {
        synchronized (lock2) {
            System.out.println("Lock2 acquired, waiting for Lock1...");
            synchronized (lock1) {
                System.out.println("Lock1 acquired.");
            }
        }
    }

    public static void main(String[] args) {
        DeadlockExample deadlockExample = new DeadlockExample();
        new Thread(deadlockExample::method1).start();
        new Thread(deadlockExample::method2).start();
    }
}
