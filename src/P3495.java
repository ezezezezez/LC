import java.util.HashMap;
import java.util.Map;

// 3495. Minimum Operations to Make Array Elements Zero
public class P3495 {
    public long minOperations(int[][] queries) {
        int n = queries.length;
        long ret = 0;
        long[] f = new long[20];
        for (int i = 1; i < 20; i++) {
            f[i] = Math.round(Math.pow(4, i)) - 1;
        }
        for (int[] query : queries) {
            long l = query[0], r = query[1];
            int tl = -1, tr = -1;
            for (int i = 1; i < f.length; i++) {
                long v = f[i];
                if (l <= v) {
                    tl = i;
                    break;
                }
            }
            for (int i = 0; i < f.length; i++) {
                long v = f[i];
                if (r > v) {
                    tr = i;
                }
            }
            // System.out.println(tl + " " + tr);
            long add = 0;
            if (tl > tr) {
                add += (r - l + 1L) * tl;
            } else {
                add += (f[tl] - l + 1L) * tl;
                add += (r - f[tr]) * (tr + 1L);
                for (int i = tl + 1; i <= tr; i++) {
                    add += (f[i] - f[i - 1]) * i;
                }
            }
            ret += (add + 1) / 2;
        }
        return ret;
    }
}
