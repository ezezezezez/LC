import java.util.ArrayDeque;
import java.util.Deque;

// 3259. Maximum Energy Boost From Two Drinks
public class P3259 {
    public long maxEnergyBoost(int[] aa, int[] bb) {
        int n = aa.length;
        long[][] f = new long[n][3];
        f[0][0] = aa[0];
        f[0][1] = bb[0];
        f[0][2] = 0L;
        for (int i = 1; i < n; i++) {
            f[i][0] = Math.max(f[i - 1][0] + aa[i], f[i - 1][2] + aa[i]);
            f[i][1] = Math.max(f[i - 1][1] + bb[i], f[i - 1][2] + bb[i]);
            f[i][2] = Math.max(Math.max(f[i - 1][0], f[i - 1][1]), f[i - 1][2]);
        }
        return Math.max(f[n - 1][2], Math.max(f[n - 1][0], f[n - 1][1]));
    }
}
