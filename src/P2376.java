import java.util.Arrays;
import java.util.PriorityQueue;

// 2376. Count Special Integers

public class P2376 {
    char[] cs;
    int[][] dp;

    public int countSpecialNumbers(int n) {
        cs = String.valueOf(n).toCharArray();
        int len = cs.length;
        dp = new int[len][1 << 10];
        for (int[] row : dp) Arrays.fill(row, -1);
        return f(0, 0, true, false);
    }

    int f(int i, int mask, boolean isLimit, boolean isNum) {
        if (i == cs.length) {
            return isNum ? 1 : 0;
        }
        if (!isLimit && isNum && dp[i][mask] >= 0) {
            return dp[i][mask];
        }
        int res = 0;
        if (!isNum) {
            res += f(i + 1, mask, false, false);
        }
        // restrictions for d goes here
        int upper = isLimit ? cs[i] - '0' : 9;
        int lower = isNum ? 0 : 1;
        // end of restrictions

        for (int d = lower; d <= upper; d++) {
            if (((mask >> d) & 1) == 0) {
                res += f(i + 1, mask | (1 << d), isLimit && d == upper, true);
            }
        }
        if (!isLimit && isNum) {
            dp[i][mask] = res;
        }
        return res;
    }
}
