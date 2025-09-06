// 3373. Maximize the Number of Target Nodes After Connecting Trees II

import java.util.*;

public class P3373 {
    List<Integer>[] adj1, adj2;

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int n = edges1.length + 1, m = edges2.length + 1;
        adj1 = new List[n];
        Arrays.setAll(adj1, kk -> new ArrayList<>());
        adj2 = new List[m];
        Arrays.setAll(adj2, kk -> new ArrayList<>());
        for (int[] edge : edges1) {
            int u = edge[0], v = edge[1];
            adj1[u].add(v);
            adj1[v].add(u);
        }
        for (int[] edge : edges2) {
            int u = edge[0], v = edge[1];
            adj2[u].add(v);
            adj2[v].add(u);
        }
        int[] parity1 = calc(adj1, 0);
        int[] parity2 = calc(adj2, 0);
        int[] cnt1 = new int[2], cnt2 = new int[2];
        for (int p : parity1) cnt1[p]++;
        for (int p : parity2) cnt2[p]++;
        int mx = Math.max(cnt2[0], cnt2[1]);
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = cnt1[parity1[i]] + mx;
        }
        return ret;
    }

    int[] calc(List<Integer>[] adj, int root) {
        int n = adj.length;
        int[] ret = new int[n];
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(root);
        boolean[] seen = new boolean[n];
        seen[root] = true;
        while (!dq.isEmpty()) {
            int sz = dq.size();
            for (int i = 0; i < sz; i++) {
                int u = dq.poll();
                for (int v : adj[u]) {
                    if (seen[v]) continue;
                    ret[v] = ret[u] ^ 1;
                    dq.offer(v);
                    seen[v] = true;
                }
            }
        }
        return ret;
    }
}
