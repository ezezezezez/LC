import java.util.*;

// 3600. Maximize Spanning Tree Stability with Upgrades

public class P3600 {
    int n;
    int[] fa;

    public int maxStability(int n, int[][] edges, int k) {
        this.n = n;
        int m = edges.length;
        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));
        fa = new int[n];
        for (int i = 0; i < n; i++) fa[i] = i;
        int cnt = 0, minS = Integer.MAX_VALUE;
        for (int i = m - 1; i >= 0; i--) {
            int[] edge = edges[i];
            if (edge[3] == 0) continue;
            minS = Math.min(minS, edge[2]);
            int u = edge[0], v = edge[1];
            int x = find(u), y = find(v);
            if (x != y) {
                fa[x] = y;
                cnt++;
                // if (cnt == n) return -1;
            } else return -1;
        }

        if (cnt == n - 1) return minS;

        List<Integer> vals = new ArrayList<>();
        for (int i = m - 1; i >= 0; i--) {
            int[] edge = edges[i];
            if (edge[3] == 1) continue;
            int u = edge[0], v = edge[1];
            int x = find(u), y = find(v);
            if (x != y) {
                vals.add(edge[2]);
                fa[x] = y;
                cnt++;
                if (cnt == n - 1) break;
            }
        }

        if (cnt != n - 1) return -1;
        for (int i = vals.size() - 1; i >= 0; i--) {
            if (k > 0) {
                k--;
                minS = Math.min(minS, 2 * vals.get(i));
            } else {
                minS = Math.min(minS, vals.get(i));
            }
        }

        return minS;
    }

    int find(int x) {
        if (fa[x] == x) return x;
        return fa[x] = find(fa[x]);
    }
}
