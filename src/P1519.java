import java.io.*;
import java.lang.*;
import java.util.*;

// 1519. Number of Nodes in the Sub-Tree With the Same Label

public class P1519 {
    int[] ret;
    Set<Integer>[] g;
    String labels;
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        this.labels = labels;
        ret = new int[n];
        g = new Set[n];
        Arrays.setAll(g, k -> new HashSet<>());
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            g[a].add(b);
            g[b].add(a);
        }
        dfs(0, -1);
        return ret;
    }

    int[] dfs(int node, int pre) {
        int[] curRet = new int[26];
        curRet[labels.charAt(node) - 'a']++;
        for (int nxt : g[node]) {
            if (nxt == pre) continue;
            int[] res = dfs(nxt, node);
            for (int i = 0; i < 26; i++) curRet[i] += res[i];
        }
        ret[node] = curRet[labels.charAt(node) - 'a'];
        return curRet;
    }
}
