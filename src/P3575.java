import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 3575. Maximum Good Subtree Score
public class P3575 {
    int n;
    List<Integer>[] adj;
    int[] vals;
    int[] masks;
    long[] resArr;
    long[][] dp;

    int mod = 1000000007;
    public int goodSubtreeSum(int[] vals, int[] par) {
        this.n = vals.length;
        adj = new List[n];
        Arrays.setAll(adj, kk -> new ArrayList<>());

        for (int i = 1; i < n; i++) {
            int p = par[i];
            adj[p].add(i);
        }

        dp = new long[n][1 << 10];
        for (long[] row : dp) Arrays.fill(row, -1);

        this.vals = vals;
        masks = new int[n];
        resArr = new long[n];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
            int val = vals[i];
            int mask = 0;
            boolean valid = true;
            while (val > 0) {
                int d = val % 10;
                if (((mask >> d) & 1) == 1) {
                    valid = false;
                    break;
                }
                mask |= 1 << d;
                val /= 10;
            }
            if (valid) {
                masks[i] = mask;
                dp[i][mask] = vals[i];
            }
        }

        // System.out.println(Arrays.toString(masks));
        dfs(0);
        long ret = 0;
        for (long res : resArr) ret = add(ret, res);

        return (int) ret;
    }

    void dfs(int u) {
        for (int v : adj[u]) {
            dfs(v);

            for (int m = 0; m < (1 << 10); m++) {
                if (dp[v][m] == -1) continue;
                int sup = ((1 << 10) - 1) ^ m;

                int sub = sup;
                do {
                    if (dp[u][sub] != -1) dp[u][m | sub] = Math.max(dp[u][m | sub], dp[u][sub] + dp[v][m]);
                    sub = (sub - 1) & sup;
                } while (sub != sup);
            }
        }

        long mx = 0;
        for (long v : dp[u]) mx = Math.max(mx, v);
        resArr[u] = Math.max(resArr[u], mx);
    }

    long add(long x, long y) {
        return (x + y) % mod;
    }
}
