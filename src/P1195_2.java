import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.function.IntConsumer;

// 1195. Fizz Buzz Multithreaded

public class P1195_2 {
    private int n;
    private int i=1;

    public P1195_2(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
//         synchronized(this){
//             while(i<=n){
//                 if(i%3==0&&i%5!=0){
//                     printFizz.run();
//                     i++;
//                     this.notifyAll();
//                 }
//                 else{
//                     this.wait();
//                 }

//             }
//         }
        synchronized (this) {
            outer:
            while (i <= n) {
                while (i % 3 != 0 || i % 5 == 0) {
                    wait();
                    continue outer;
                }
                printFizz.run();
                i++;
                notifyAll();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
//         synchronized(this){
//             while(i<=n){
//                 if(i%5==0&&i%3!=0){
//                     printBuzz.run();
//                     i++;
//                     this.notifyAll();
//                 }
//                 else{
//                     this.wait();
//                 }

//             }
//         }
        synchronized (this) {
            outer:
            while (i <= n) {
                while (i % 5 != 0 || i % 3 == 0) {
                    wait();
                    continue outer;
                }
                printBuzz.run();
                i++;
                notifyAll();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
//         synchronized(this){
//             while(i<=n){
//                 if(i%3==0&&i%5==0){
//                     printFizzBuzz.run();
//                     i++;
//                     this.notifyAll();
//                 }
//                 else{
//                     this.wait();
//                 }

//             }
//         }
        synchronized (this) {
            outer:
            while (i <= n) {
                while (i % 3 != 0 || i % 5 != 0) {
                    wait();
                    continue outer;
                }
                printFizzBuzz.run();
                i++;
                notifyAll();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
//         synchronized(this){
//             while(i<=n){
//                 if(i%3!=0&&i%5!=0){
//                     printNumber.accept(i);
//                     i++;
//                     this.notifyAll();
//                 }
//                 else{
//                     this.wait();
//                 }

//             }
//         }
        synchronized (this) {
            outer:
            while (i <= n) {
                while (i % 3 == 0 || i % 5 == 0) {
                    wait();
                    continue outer;
                }
                printNumber.accept(i);
                i++;
                notifyAll();
            }
        }
    }
}
