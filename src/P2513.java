import java.io.*;
import java.lang.*;
import java.util.*;

// 2513. Minimize the Maximum of Two Arrays

public class P2513 {
    public int minimizeSet(int d1, int d2, int u1, int u2) {
        long lcm = 1L * d1 * d2 / gcd(d1, d2);
        long lo = 1, hi = Long.MAX_VALUE / 2;
        long t = -1;
        while (lo <= hi) {
            long mid = (lo + hi) >> 1;
            long two = mid / d1 - mid / lcm;
            long one = mid / d2 - mid / lcm;
            long res = mid - mid / d1 - mid / d2 + mid / lcm;
            if (res >= Math.max(0, u2 - two) + Math.max(0, u1 - one)) {
                t = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return (int)t;
    }

    long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
