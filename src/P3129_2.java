import java.util.Arrays;

// 3129. Find All Possible Stable Binary Arrays I
public class P3129_2 {
    int[][][] memo;
    int limit;

    int mod = (int) Math.round(1e9 + 7);
    public int numberOfStableArrays(int zero, int one, int limit) {
        this.limit = limit;
        this.memo = new int[2][one + 1][zero + 1];
        for (int[][] a : memo) {
            for (int[] b : a) {
                Arrays.fill(b, -1);
            }
        }
        // System.out.println(a + " " + b);
        int ret = dfs(0, one, zero - 1) + dfs(1, one - 1, zero);
        return ret % mod;
    }

    int dfs(int cur, int one, int zero) {
        if (cur == 0 && one == 0 && zero == -1) return 1;
        if (cur == 1 && zero == 0 && one == -1) return 1;
        if (one < 0 || zero < 0) return 0;
        if (one == 0 && zero == 0) return 1;
        if (memo[cur][one][zero] != -1) {
            return memo[cur][one][zero];
        }
        int cnt = 0;
        if (one > 0) {
            cnt = (cnt + dfs(1, one - 1, zero)) % mod;
            if (cur == 1) cnt = (cnt - dfs(0, one - limit, zero - 1) + mod) % mod;
        }
        if (zero > 0) {
            cnt = (cnt + dfs(0, one, zero - 1)) % mod;
            if (cur == 0) cnt = (cnt - dfs(1, one - 1, zero - limit) + mod) % mod;
        }
        memo[cur][one][zero] = cnt;
        return cnt;
    }
}
