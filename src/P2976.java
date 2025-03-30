import java.lang.invoke.VarHandle;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 2976. Minimum Cost to Convert String I
public class P2976 {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int n = source.length();
        int m = original.length;
        int[][] graph = new int[26][26];
        for (int[] row : graph) Arrays.fill(row, INF);
        for (int i = 0; i < 26; i++) graph[i][i] = 0;
        for (int i = 0; i < m; i++) {
            graph[original[i] - 'a'][changed[i] - 'a'] = Math.min(cost[i], graph[original[i] - 'a'][changed[i] - 'a']);
        }
        int[][] d = fw(graph);
        // printGrid2D(d);
        long ret = 0;
        for (int i = 0; i < n; i++) {
            char s = source.charAt(i), t = target.charAt(i);
            if (s != t) {
                if (d[s - 'a'][t - 'a'] == INF) return -1;
            }
            ret += d[s - 'a'][t - 'a'];
        }
        return ret;
    }

    void printGrid2D(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    int INF = 0x3f3f3f3f;
    int[][] fw(int[][] graph) {
        int n = graph.length;
        int[][] d = new int[n][n];
        for (int[] row : d) {
            Arrays.fill(row, INF);
        }
        for (int i = 0; i < n; i++) {
            d[i][i] = 0;
            for (int j = 0; j < n; j++) {
                d[i][j] = graph[i][j];
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (d[i][k] < INF && d[k][j] < INF && d[i][k] + d[k][j] < d[i][j]) {
                        d[i][j] = d[i][k] + d[k][j];
                    }
                }
            }
        }
        return d;
    }
}
