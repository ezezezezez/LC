import java.util.*;
import java.io.*;
import java.lang.*;

// 1042. Flower Planting With No Adjacent

public class P1042 {
    Set<Integer>[] g;
    int[] ret;
    public int[] gardenNoAdj(int n, int[][] paths) {
        ret = new int[n];
        g = new Set[n];
        Arrays.setAll(g, k -> new HashSet<>());
        for (int[] path : paths) {
            int a = path[0] - 1, b = path[1] - 1;
            g[a].add(b);
            g[b].add(a);
        }

        for (int i = 0; i < n; i++) {
            if (ret[i] != 0) continue;
            dfs(i);
        }

        return ret;
    }

    void dfs(int node) {
        if (ret[node] != 0) return;
        boolean[] available = new boolean[5];

        for (int nxt : g[node]) {
            available[ret[nxt]] = true;
        }

        for (int i = 1; i <= 4; i++) {
            if (!available[i]) {
                ret[node] = i;
                break;
            }
        }

        for (int nxt : g[node]) {
            if (ret[nxt] == 0) {
                dfs(nxt);
            }
        }
    }
}
