import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.concurrent.Semaphore;

// 1117. Building H2O

public class P1117 {
    static class H2O {

        int flag;
        final Object lock = new Object();

        public H2O() {

        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            while (true) {
                synchronized (lock) {
                    if (flag == 0) {
                        lock.wait();
                        continue;
                    }
                    // releaseHydrogen.run() outputs "H". Do not change or remove this line.
                    releaseHydrogen.run();
                    flag = (flag + 1) % 3;
                    lock.notifyAll();
                    break;
                }
            }
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            while (true) {
                synchronized (lock) {
                    if (flag == 1 || flag == 2) {
                        lock.wait();
                        continue;
                    }
                    // releaseOxygen.run() outputs "O". Do not change or remove this line.
                    releaseOxygen.run();
                    flag = (flag + 1) % 3;
                    lock.notifyAll();
                    break;
                }
            }
        }
    }

    static class H2O_2 {

        int flag;

        public H2O_2() {

        }

        public synchronized void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            while (flag == 0) wait();
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            flag = flag == 1 ? 2 : 0;
            // notify();
            notifyAll();
        }

        public synchronized void oxygen(Runnable releaseOxygen) throws InterruptedException {
            while (flag != 0) wait();
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            flag = 1;
            // notify();
            notifyAll();
        }
    }
}
