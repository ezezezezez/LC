import java.io.*;
import java.lang.*;
import java.util.*;

// 1466. Reorder Routes to Make All Paths Lead to the City Zero

public class P1466 {
    Set<Integer>[] g, rg;
    int rev;
    public int minReorder(int n, int[][] connections) {
        g = new Set[n]; rg = new Set[n];
        Arrays.setAll(g, k -> new HashSet<>());
        Arrays.setAll(rg, k -> new HashSet<>());
        for (int[] connection : connections) {
            int a = connection[0], b = connection[1];
            g[a].add(b);
            rg[b].add(a);
        }
        dfs(0, -1);
        return n - 1 - rev;
    }

    void dfs(int node, int pre) {
        for (int nxt : g[node]) {
            if (nxt == pre) continue;
            dfs(nxt, node);
        }
        for (int rnxt : rg[node]) {
            if (rnxt == pre) continue;
            rev++;
            dfs(rnxt, node);
        }
    }
}
