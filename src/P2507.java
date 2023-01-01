import java.io.*;
import java.lang.*;
import java.util.*;

// 2502. Design Memory Allocator

public class P2507 {
    public int smallestValue(int n) {
        List<Integer> primes = new ArrayList<>();
        primes.add(2);
        for (int i = 3; i * i <= n; i += 2) {
            boolean flag = true;
            for (int j = 3; j < i; j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) primes.add(i);
        }
        // System.out.println(primes);
        while (true) {
            int t = 0;
            int old = n;
            for (int i = 0; i < primes.size() && primes.get(i) * primes.get(i) <= n; i++) {
                int prime = primes.get(i);
                while (n % prime == 0) {
                    t += prime;
                    n /= prime;
                }
            }
            if (n > 1) t += n;
            n = t;
            if (old == n) break;
            // System.out.println(n);
        }
        return n;
    }
}
