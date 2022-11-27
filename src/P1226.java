// 1226. The Dining Philosophers

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class P1226 {
    final ReentrantLock[] locks = new ReentrantLock[]{
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
    };

    final Semaphore ppl = new Semaphore(4);

    public P1226() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        //
        int leftFork = (philosopher + 1) % 5;
        int rightFork = philosopher;

        ppl.acquire();

        locks[leftFork].lock();
        locks[rightFork].lock();

        pickLeftFork.run();
        pickRightFork.run();

        eat.run();

        putLeftFork.run();
        putRightFork.run();

        locks[leftFork].unlock();
        locks[rightFork].unlock();

        ppl.release();
    }
}
