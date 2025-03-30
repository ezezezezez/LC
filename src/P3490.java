import java.util.*;

// 3490. Count Beautiful Numbers
public class P3490 {
    int n;
    int[] csHigh;
    int[] csLow;
    // int[] dp;
    Map<Integer, Map<Integer, Map<Integer, Integer>>> dp2;
    int limit = 9;
    int diffLohi;

    public int beautifulNumbers(int l, int r) {
        char[] highCharArr = String.valueOf(r).toCharArray();
        char[] lowCharArr = String.valueOf(l).toCharArray();
        n = highCharArr.length;
        csHigh = new int[n];
        csLow = new int[lowCharArr.length];
        for (int i = 0; i < n; i++) {
            csHigh[i] = highCharArr[i] - '0';
        }
        for (int i = 0; i < lowCharArr.length; i++) {
            csLow[i] = lowCharArr[i] - '0';
        }
        diffLohi = n - lowCharArr.length;
        dp2 = new HashMap<>();
        return f4(0, 0, 1, true, true);
    }

    int f4(int i, int sum, int prod, boolean lowerLimit, boolean upperLimit) {
        if (i == n) {
            return prod % sum == 0 ? 1 : 0;
        }
        if (!lowerLimit && !upperLimit && metBefore(i, sum, prod)) {
            return dp2.get(i).get(sum).get(prod);
        }
        int lo = lowerLimit && i >= diffLohi ? csLow[i - diffLohi] : 0;
        int hi = upperLimit ? csHigh[i] : 9;
        int res = 0, d = lo;
        if (lowerLimit && i < diffLohi) {
            res += f4(i + 1, sum, prod, true, false);
            d = 1;
        }

        for (; d <= Math.min(hi, limit); d++) {
            res += f4(i + 1, sum + d, prod * d, lowerLimit && d == lo, upperLimit && d == hi);
        }
        if (!lowerLimit && !upperLimit) {
            saveToMemo(i, sum, prod, res);
        }
        return res;
    }

    boolean metBefore(int i, int sum, int prod) {
        if (!dp2.containsKey(i)) return false;
        if (!dp2.get(i).containsKey(sum)) return false;
        if (!dp2.get(i).get(sum).containsKey(prod)) return false;
        return true;
    }

    void saveToMemo(int i, int sum, int prod, int res) {
        Map<Integer, Map<Integer, Integer>> m1 = dp2.computeIfAbsent(i, k -> new HashMap<>());
        Map<Integer, Integer> m2 = m1.computeIfAbsent(sum, k -> new HashMap<>());
        m2.put(prod, res);
    }
}
