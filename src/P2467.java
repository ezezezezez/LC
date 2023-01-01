import java.io.*;
import java.lang.*;
import java.util.*;

// 2467. Most Profitable Path in a Tree

public class P2467 {
    int INF = 0x3f3f3f3f;
    int ret = -INF;
    Set<Integer>[] g;
    int bob;
    int sum;
    int sum2;
    int[] amount;
    int part2;
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        this.bob = bob;
        this.amount = amount;
        int m = edges.length, n = m + 1;
        g = new Set[n];
        Arrays.setAll(g, k -> new HashSet<>());
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            g[a].add(b);
            g[b].add(a);
        }
        part2 = g[bob].size() == 1 ? 0 : -INF;
        List<Integer> path = new ArrayList<>();
        dfs2(bob, -1, path);
        int cur = 0;
        for (int i = 0; i < path.size(); i++) {
            if (i < (path.size() + 1) / 2) {
                int j = path.size() - 1 - i;
                if (i < j) {
                    cur += amount[path.get(i)];
                } else {
                    cur += amount[path.get(i)] / 2;
                    amount[path.get(i)] /= 2;
                }
            } else {
                amount[path.get(i)] = 0;
            }
        }
        dfs(0, -1);
        // System.out.println(cur + " " + part2 + " " + ret);

        return Math.max(ret, cur + part2);
    }

    void dfs(int node, int pre) {
        sum += amount[node];
        if (g[node].size() == 1 && g[node].iterator().next() == pre) {
            ret = Math.max(ret, sum);
            sum -= amount[node];
            return;
        }
        for (int nxt : g[node]) {
            if (nxt == pre) continue;
            dfs(nxt, node);
        }
        sum -= amount[node];
    }

    boolean dfs2(int node, int pre, List<Integer> path) {
        if (node == 0) {
            path.add(0);
            return true;
        }
        boolean flag = false;
        for (int nxt : g[node]) {
            if (nxt == pre) continue;
            if (dfs2(nxt, node, path)) {
                path.add(node);
                flag = true;
            }
        }
        return flag;
    }
}
