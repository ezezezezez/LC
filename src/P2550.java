import java.io.*;
import java.lang.*;
import java.util.*;

public class P2550 {
    public int monkeyMove(int n) {
        int mod = (int)(1e9 + 7);
        return (pow(2, n) - 2 + mod) % mod;
    }

    // fast power
    int pow(long x, long n) {
        long ret = 1, mod = (long)(1e9 + 7);
        while (n > 0) {
            if (n % 2 == 1) {
                ret = ret * x % mod;
            }
            x = x * x % mod;
            n /= 2;
        }
        return (int)ret;
    }
}
