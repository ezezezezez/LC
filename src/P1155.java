import java.util.*;
import java.io.*;
import java.lang.*;

// 1155. Number of Dice Rolls With Target Sum

public class P1155 {
    int mod = (int)(1e9 + 7);
    public int numRollsToTarget(int n, int k, int target) {
        int[][] f = new int[n + 1][target + 1];
        f[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                for (int t = 1; t <= k; t++) {
                    int rem = j - t;
                    if (rem < i - 1) continue;
                    if (rem > (i - 1) * k) continue;
                    f[i][j] = (f[i][j] + f[i - 1][rem]) % mod;
                }
            }
        }

        return f[n][target];
    }
}
