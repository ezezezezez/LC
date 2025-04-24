import java.math.BigInteger;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// 3519. Count Numbers with Non-Decreasing Digits
public class P3519 {
    int n;
    int[] csHigh;
    int[] csLow;
    // int[] dp;
    Map<Integer, Map<Integer, Integer>> dp2;
    int diffLohi;
    int b;
    int mod = (int) Math.round(1e9 + 7);
    public int countNumbers(String l, String r, int b) {
        this.b = b;
        char[] highCharArr = new BigInteger(r).toString(b).toCharArray();
        char[] lowCharArr = new BigInteger(l).toString(b).toCharArray();
        // System.out.println(Arrays.toString(highCharArr));
        // System.out.println(Arrays.toString(lowCharArr));
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
        // System.out.println(diffLohi);
        return f4(0, -1, true, true);
    }

    int f4(int i, int last, boolean lowerLimit, boolean upperLimit) {
        if (i == n) {
            return 1;
        }
        if (!lowerLimit && !upperLimit && metBefore(i, last)) {
            return dp2.get(i).get(last);
        }
        int lo = lowerLimit && i >= diffLohi ? csLow[i - diffLohi] : 0;
        int hi = upperLimit ? csHigh[i] : (b - 1);
        int res = 0, d = lo;
        if (lowerLimit && i < diffLohi) {
            res = add(res, f4(i + 1, -1, true, false));
            d = 1;
        }

        d = Math.max(d, last);
        for (; d <= hi; d++) {
            res = add(res, f4(i + 1, d, lowerLimit && d == lo, upperLimit && d == hi));
        }
        if (!lowerLimit && !upperLimit) {
            saveToMemo(i, last, res);
        }
        return res;
    }

    boolean metBefore(int i, int last) {
        if (!dp2.containsKey(i)) return false;
        if (!dp2.get(i).containsKey(last)) return false;
        return true;
    }

    void saveToMemo(int i, int last, int res) {
        Map<Integer, Integer> m1 = dp2.computeIfAbsent(i, k -> new HashMap<>());
        m1.put(last, res);
    }

    int add(int a, int b) {
        return (a + b) % mod;
    }
}
