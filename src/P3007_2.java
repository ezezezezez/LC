import java.util.HashMap;
import java.util.Map;

// 3007. Maximum Number That Sum of the Prices Is Less Than or Equal to K
public class P3007_2 {
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
            dp2.clear();
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
    // int[] dp;
    Map<Integer, Map<Long, Long>> dp2 = new HashMap<>();
    int limit = 9;
    int diffLohi;

    long f4(int i, long cnt, boolean lowerLimit, boolean upperLimit) {
        if (i == n) {
            return cnt;
        }
        if (!lowerLimit && !upperLimit && metBefore(i, cnt)) {
            return dp2.get(i).get(cnt);
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

    boolean metBefore(int i, long cnt) {
        if (!dp2.containsKey(i)) return false;
        if (!dp2.get(i).containsKey(cnt)) return false;
        return true;
    }

    void saveToMemo(int i, long cnt, long res) {
        Map<Long, Long> m1 = dp2.computeIfAbsent(i, k -> new HashMap<>());
        m1.put(cnt, res);
    }
}
