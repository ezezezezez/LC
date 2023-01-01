import java.io.*;
import java.lang.*;
import java.util.*;

// 2457. Minimum Addition to Make Integer Beautiful

public class P2457 {
    public long makeIntegerBeautiful(long n, int target) {
        int[] ds = new int[20];
        int idx = n == 0 ? 1 : 0;
        long t = n;
        while (t > 0) {
            ds[idx++] = (int)(t % 10);
            t /= 10;
        }
        int sum = 0;
        for (int i = 0; i < idx; i++) sum += ds[i];
        if (sum <= target) return 0;
        long cur = 10;
        long ret = 0;
        while (true) {
            long rem = n % cur;
            ret = cur - rem;
            long nxt = n + ret;
            sum = 0;
            t = nxt;
            while (t > 0) {
                sum += t % 10;
                t /= 10;
            }
            if (sum <= target) break;
            cur *= 10;
        }
        return ret;
    }
}
