// 935. Knight Dialer

import java.util.Arrays;

public class P935_2 {
    int[][] next = new int[][] {
            {4, 6}, {6, 8}, {7, 9}, {4, 8}, {3, 9, 0}, {}, {1, 7, 0}, {2, 6}, {1, 3}, {2, 4}
    };
    int[][] memo;
    int mod = 1000000007;
    public int knightDialer(int n) {
        memo = new int[10][n];
        for (int[] row : memo) Arrays.fill(row, -1);

        int ret = 0;
        for (int i = 0; i < 10; i++) {
            ret = (ret + dfs(i, n - 1)) % mod;
        }

        return ret;
    }

    int dfs(int i, int j) {
        if (j == 0) return 1;
        if (memo[i][j] != -1) return memo[i][j];

        int ret = 0;
        for (int nxt : next[i]) {
            ret = (ret + dfs(nxt, j - 1)) % mod;
        }

        memo[i][j] = ret;
        return ret;
    }
}
