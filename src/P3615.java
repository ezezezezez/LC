import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 3615. Longest Palindromic Path in Graph

public class P3615 {
    public int maxLen(int n, int[][] edges, String label) {
        Set<Integer>[] adj = new Set[n];
        Arrays.setAll(adj, kk -> new HashSet<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
        }
        int[][][] dp = new int[1 << n][n][n];
        for (int[][] a : dp) {
            for (int[] b : a) {
                Arrays.fill(b, -1);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[0][i][j] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            dp[1 << i][i][i] = 1;
        }

        for (int i = 1; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if ((i & (1 << j)) == 0) continue;
                    if ((i & (1 << k)) == 0) continue;
                    if (label.charAt(j) != label.charAt(k)) continue;

                    int prev = i - (1 << j) - (1 << k);
                    if (prev == 0) {
                        if (adj[j].contains(k)) {
                            dp[i][j][k] = dp[i][k][j] = 2;
                        }
                        continue;
                    }
                    for (int p = 0; p < n; p++) {
                        for (int q = p; q < n; q++) {
                            if (dp[prev][p][q] == -1) continue;
                            if ((prev & (1 << p)) == 0) continue;
                            if ((prev & (1 << q)) == 0) continue;
                            if (label.charAt(p) != label.charAt(q)) continue;
                            if (adj[j].contains(p) && adj[k].contains(q)) {
                                dp[i][j][k] = dp[i][k][j] = Math.max(dp[i][j][k], dp[prev][p][q] + 2);
                            } else if (adj[j].contains(q) && adj[k].contains(p)) {
                                dp[i][j][k] = dp[i][k][j] = Math.max(dp[i][j][k], dp[prev][p][q] + 2);
                            }
                        }
                    }
                }
            }
        }

        int ret = 0;
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    ret = Math.max(ret, dp[i][j][k]);
                }
            }
        }

        return ret;
    }
}
