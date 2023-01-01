import java.io.*;
import java.lang.*;
import java.util.*;

// 2492. Minimum Score of a Path Between Two Cities

public class P2492 {
    Set<Edge>[] g;
    int ret = Integer.MAX_VALUE;
    boolean[] vis;
    public int minScore(int n, int[][] roads) {
        vis = new boolean[n];
        int m = roads.length;
        g = new Set[n];
        Arrays.setAll(g, k -> new HashSet<>());
        for (int[] road : roads) {
            int a = road[0] - 1, b = road[1] - 1, dist = road[2];
            g[a].add(new Edge(b, dist));
            g[b].add(new Edge(a, dist));
        }
        dfs(0, -1);
        return ret;
    }

    void dfs(int node, int pre) {
        vis[node] = true;
        for (Edge nxtEdge : g[node]) {
            int nxt = nxtEdge.to, dist = nxtEdge.dist;
            ret = Math.min(ret, dist);
            if (vis[nxt]) continue;
            dfs(nxt, node);
        }
    }

    static class Edge {
        int to, dist;
        Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
}
