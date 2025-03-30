import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 2925. Maximum Score After Applying Operations on a Tree
public class P2925 {
    long ret = 0;
    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        int m = edges.length, n = m + 1;
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) g.add(new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            g.get(u).add(v);
            g.get(v).add(u);
        }
        long sum = 0;
        for (int v : values) sum += v;
        dfs(g, 0, -1, values, sum);
        return ret;
    }

    long dfs(List<List<Integer>> g, int cur, int pre, int[] values, long sum) {
        long t = 0;
        for (int nxt : g.get(cur)) {
            if (nxt == pre) continue;
            t += dfs(g, nxt, cur, values, sum);
        }
        long res = Math.min(values[cur], t == 0 ? values[cur] : t);
        // System.out.println(cur + " " + res);
        ret = sum - res;
        return res;
    }
}
