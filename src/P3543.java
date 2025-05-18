import java.util.*;

// 3543. Maximum Weighted K-Edge Path
public class P3543 {
    public int maxWeight(int n, int[][] edges, int k, int t) {
        int m = edges.length;
        if (k > m) return -1;
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, kk -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            g[u].add(new int[] {v, w});
        }
        memo = new int[n][k + 1][t];
        for (int[][] a : memo) {
            for (int[] b : a) {
                Arrays.fill(b, -1);
            }
        }
        int ret = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int x = dfs(i, g, k, t, 0, 0);
            if (x >= 0) ret = Math.max(ret, x);
        }
        return ret == Integer.MIN_VALUE ? -1 : ret;
    }

    int[][][] memo;

    int dfs(int cur, List<int[]>[] g, int k, int t, int cnt, int sum) {
        if (cnt == k) {
            return sum;
        }
        if (memo[cur][cnt][sum] != -1) return memo[cur][cnt][sum];
        int mx = 0;
        for (int[] nxt : g[cur]) {
            int v = nxt[0], w = nxt[1];
            if (sum + w < t) {
                int x = dfs(v, g, k, t, cnt + 1, sum + w);
                if (x >= 0) mx = Math.max(mx, x);
            }
        }
        memo[cur][cnt][sum] = mx == 0 ? -2 : mx;
        return memo[cur][cnt][sum];
    }
}
