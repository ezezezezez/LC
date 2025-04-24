import java.util.Arrays;

// 3129. Find All Possible Stable Binary Arrays I
public class P3129 {
    int[][][][] memo;
    int limit;

    int mod = (int) Math.round(1e9 + 7);
    int len;
    public int numberOfStableArrays(int zero, int one, int limit) {
        long ret = 0;
        len = zero + one;
        this.limit = limit;
        memo = new int[len][2][limit + 1][zero + 1];
        for (int[][][] a : memo) {
            for (int[][] b : a) {
                for (int[] c : b) {
                    Arrays.fill(c, -1);
                }
            }
        }
        return dfs(0, -1, 0, zero);
    }

    int dfs(int i, int pre, int preCnt, int zero) {
        if (i == len) {
            return 1;
        }
        if (pre != -1 && memo[i][pre][preCnt][zero] != -1L) {
            return memo[i][pre][preCnt][zero];
        }
        int rest = len - i;
        int one = rest - zero;
        int cnt = 0;
        if (preCnt == limit) {
            int pick = pre ^ 1;
            if (pick == 0 && zero == 0) return 0;
            if (pick == 1 && one == 0) return 0;
            cnt += dfs(i + 1, pick, 1, zero - (pick == 0 ? 1 : 0));
            cnt %= mod;
        } else {
            if (zero > 0) {
                cnt += dfs(i + 1, 0, pre == 0 ? preCnt + 1 : 1, zero - 1);
                cnt %= mod;
            }
            if (one > 0) {
                cnt += dfs(i + 1, 1, pre == 1 ? preCnt + 1 : 1, zero);
                cnt %= mod;
            }
        }
        if (pre != -1) memo[i][pre][preCnt][zero] = cnt;
        return cnt;
    }
}
