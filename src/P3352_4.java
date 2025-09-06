import java.util.Arrays;

// 3352. Count K-Reducible Numbers Less Than N

public class P3352_4 {
    int n;
    int k;
    int[] csHigh;
    int[] csLow;
    int[][] dp;
    // Map<Integer, Map<Integer, Integer>> memo;
    int diffLohi;

    public int countKReducibleNumbers(String s, int k) {
        this.k = k;
        char[] highCharArr = s.toCharArray();
        char[] lowCharArr = Integer.toBinaryString(1).toCharArray();
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
        dp = new int[s.length()][s.length() + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        // memo = new HashMap<>();
        // System.out.println(diffLohi);
        return f4(0, 0, true, true);
    }

    int f4(int i, int last, boolean lowerLimit, boolean upperLimit) {
        if (i == n) {
            if (last == 0) return 0;
            if (upperLimit) return 0;
            return getDepth(last) + 1 <= k ? 1 : 0;
        }
        if (!lowerLimit && !upperLimit && dp[i][last] != -1) {
            return dp[i][last];
        }
        int lo = lowerLimit && i >= diffLohi ? csLow[i - diffLohi] : 0;
        int hi = upperLimit ? csHigh[i] : 1;
        int res = 0;
        int d = lo;
        if (lowerLimit && i < diffLohi) {
            res = add(res, f4(i + 1, 0, true, false));
            d = 1;
        }

        for (; d <= hi; d++) {
            res = add(res, f4(i + 1, last + d, lowerLimit && d == lo, upperLimit && d == hi));
        }
        if (!lowerLimit && !upperLimit) {
            dp[i][last] = res;
        }
        return res;
    }

    int getDepth(int x) {
        int cnt = 0;
        while (x != 1) {
            x = Integer.bitCount(x);
            cnt++;
        }
        return cnt;
    }

    int mod = 1000000007;

    int add(int a, int b) {
        return (a + b) % mod;
    }
}
