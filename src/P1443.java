import java.io.*;
import java.lang.*;
import java.util.*;

// 1443. Minimum Time to Collect All Apples in a Tree

public class P1443 {
    boolean[] subHasApples;
    List<Boolean> hasApple;
    Set<Integer>[] g;
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        g = new Set[n];
        Arrays.setAll(g, k -> new HashSet<>());
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            g[a].add(b);
            g[b].add(a);
        }
        subHasApples = new boolean[n];
        this.hasApple = hasApple;
        dfs(0, -1);
        // System.out.println(Arrays.toString(subHasApples));

        dfs2(0, -1);
        return ret;
    }

    int ret;

    boolean dfs(int node, int pre) {
        subHasApples[node] = hasApple.get(node);
        for (int nxt : g[node]) {
            if (nxt == pre) continue;
            subHasApples[node] = dfs(nxt, node) || subHasApples[node];
        }
        return subHasApples[node];
    }

    void dfs2(int node, int pre) {
        if (!subHasApples[node]) return;

        for (int nxt : g[node]) {
            if (nxt == pre) continue;
            if (subHasApples[nxt]) {
                ret++;
                dfs2(nxt, node);
                ret++;
            }
        }
    }
}
