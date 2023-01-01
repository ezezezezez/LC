import java.io.*;
import java.lang.*;
import java.util.*;

// 1922. Count Good Numbers

public class P1922 {
    public int countGoodNumbers(long n) {
        long mod = (long)(1e9 + 7);
        long cur = n % 2 == 1 ? 5 : 1;
        n /= 2;
        long x = 20;
        while (n > 0) {
            if (n % 2 == 1) cur = cur * x % mod;
            x = x * x % mod;
            n /= 2;
        }
        return (int)cur;
    }
}
