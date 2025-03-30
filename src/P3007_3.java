import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 3007. Maximum Number That Sum of the Prices Is Less Than or Equal to K
public class P3007_3 {
    int x;
    public long findMaximumNumber(long k, int x) {
        this.x = x;
        long ret = 0;

        char[] lowCharArr = Long.toBinaryString(1L).toCharArray();
        csLow = new int[lowCharArr.length];
        for (int i = 0; i < lowCharArr.length; i++) {
            csLow[i] = lowCharArr[i] - '0';
        }

        long lo = 1, hi = (long) 1e15, t = -1;
        // long lo = 1, hi = (long) 9, t = -1;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            char[] highCharArr = Long.toBinaryString(mid).toCharArray();
            n = highCharArr.length;
            csHigh = new int[n];
            for (int i = 0; i < n; i++) {
                csHigh[i] = highCharArr[i] - '0';
            }
            diffLohi = n - lowCharArr.length;
            for (long[] row : dp) Arrays.fill(row, -1);
            // dp2.clear();
            long count = f4(0, 0, true, true);
            // System.out.println(lo + " " + hi + " " + mid + " " + count + " " + dp2);

            if (count <= k) {
                t = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return t;
    }

    int n;
    int[] csHigh;
    int[] csLow;
    long[][] dp = new long[70][70];
    // Map<Integer, Map<Long, Long>> dp2 = new HashMap<>();
    int limit = 9;
    int diffLohi;

    long f4(int i, int cnt, boolean lowerLimit, boolean upperLimit) {
        if (i == n) {
            return cnt;
        }
        if (!lowerLimit && !upperLimit && metBefore(i, cnt)) {
            return dp[i][cnt];
        }
        int lo = lowerLimit && i >= diffLohi ? csLow[i - diffLohi] : 0;
        int hi = upperLimit ? csHigh[i] : 1;
        long res = 0, d = lo;
        if (lowerLimit && i < diffLohi) {
            res += f4(i + 1, cnt, true, false);
            d = 1;
        }

        for (; d <= Math.min(hi, limit); d++) {
            res += f4(i + 1, cnt + (d == 1 && (n - i) % x == 0 ? 1 : 0), lowerLimit && d == lo, upperLimit && d == hi);
        }
        if (!lowerLimit && !upperLimit) {
            saveToMemo(i, cnt, res);
        }
        return res;
    }

    boolean metBefore(int i, int cnt) {
        return dp[i][cnt] != -1;
    }

    void saveToMemo(int i, int cnt, long res) {
        dp[i][cnt] = res;
    }
}
