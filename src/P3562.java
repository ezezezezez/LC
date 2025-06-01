import java.util.*;

// 3562. Maximum Profit from Trading Stocks with Discounts
public class P3562 {
    List<Integer>[] adj;
    int[] present, future;
    int[][][] memo;
    int budget;

    public int maxProfit(int n, int[] present, int[] future, int[][] edges, int budget) {
        adj = new List[n];
        this.present = present;
        this.future = future;
        this.budget = budget;
        Arrays.setAll(adj, kk -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0] - 1, v = edge[1] - 1;
            adj[u].add(v);
        }

        memo = new int[n][2][];

        int[] temp = dfs(0, 0);
        int ret = INF;
        for (int v : temp) ret = Math.max(ret, v);

        return ret;
    }

    int INF = Integer.MIN_VALUE / 2;

    int[] dfs(int node, int discount) {
        if (memo[node][discount] != null) return memo[node][discount];
        int[] nodeBuyRes = new int[budget + 1], nodeSkipRes = new int[budget + 1];
        Arrays.fill(nodeBuyRes, INF);
        Arrays.fill(nodeSkipRes, INF);
        nodeSkipRes[0] = 0;
        int cost = discount == 1 ? present[node] / 2 : present[node];
        if (cost <= budget) nodeBuyRes[cost] = future[node] - cost;

        for (int v : adj[node]) {
            int[] childRes1 = dfs(v, 0);

            nodeSkipRes = merge(nodeSkipRes, childRes1, 0);

            if (cost <= budget) {
                int[] childRes2 = dfs(v, 1);
                nodeBuyRes = merge(nodeBuyRes, childRes2, cost);
            }
        }

        // int[] nodeRes = new int[budget + 1];
        for (int i = 0; i <= budget; i++) {
            nodeSkipRes[i] = Math.max(nodeSkipRes[i], nodeBuyRes[i]);
        }

        memo[node][discount] = nodeSkipRes;
        // System.out.println(node + " " + discount + " " + cost);
        // System.out.println(Arrays.toString(nodeRes));
        return nodeSkipRes;
    }

    int[] merge(int[] A, int[] B, int startCost) {
        for (int i = budget; i >= startCost; i--) {
            if (A[i] == INF) continue;
            for (int j = budget - i; j >= 0; j--) {
                if (B[j] == INF) continue;
                A[i + j] = Math.max(A[i + j], A[i] + B[j]);
            }
        }
        return A;
    }
}
