import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 3544. Subtree Inversion Sum
public class P3544 {
    int[] parent;
    boolean[] isChild;
    int[] nums;
    List<Integer>[] g;
    int k;
    long[][][] memo;
    public long subtreeInversionSum(int[][] edges, int[] nums, int k) {
        this.k = k;
        this.nums = nums;
        int m = edges.length, n = m + 1;
        g = new List[n];
        Arrays.setAll(g, kk -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            g[u].add(v);
            g[v].add(u);
        }
        parent = new int[n];
        isChild = new boolean[n];
        dfs(0, -1);
        memo = new long[n][2][k + 1];
        for (long[][] a : memo) {
            for (long[] b : a) {
                Arrays.fill(b, -1);
            }
        }
        return Math.max(go(0, 0, 0), go(0, 1, k));
    }

    long go(int cur, int flip, int level) {
        if (isChild[cur]) {
            if (level == 0) return Math.max(nums[cur], -nums[cur]);
            return flip == 1 ? -nums[cur] : nums[cur];
        }
        if (memo[cur][flip][level] != -1) return memo[cur][flip][level];
        long ret = flip == 1 ? -nums[cur] : nums[cur];
        for (int nxt : g[cur]) {
            if (nxt == parent[cur]) continue;
            long v1 = go(nxt, flip, Math.max(level - 1, 0));
            if (level <= 1) {
                long v2 = go(nxt, flip ^ 1, k);
                ret += Math.max(v1, v2);
            } else {
                ret += v1;
            }
        }
        memo[cur][flip][level] = ret;
        return ret;
    }

    void dfs(int cur, int pre) {
        if (pre != -1) parent[cur] = pre;
        boolean hasChild = false;
        for (int nxt : g[cur]) {
            if (nxt == pre) continue;
            hasChild = true;
            dfs(nxt, cur);
        }
        if (!hasChild) isChild[cur] = true;
    }
}
