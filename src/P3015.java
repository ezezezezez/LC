import java.util.Arrays;

// 3015. Count the Number of Houses at a Certain Distance I
public class P3015 {
    public int[] countOfPairs(int n, int x, int y) {
        int[] ret = new int[n];
        int[][] g = new int[n][n];
        for (int[] row : g) Arrays.fill(row, INF);
        for (int i = 0; i < n; i++) g[i][i] = 0;
        for (int i = 0; i < n - 1; i++) {
            g[i][i + 1] = g[i + 1][i] = 1;
        }
        g[x - 1][y - 1] = g[y - 1][x - 1] = 1;
        g = fw(g);
        // for (int[] row : g) System.out.println(Arrays.toString(row));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (g[i][j] != INF) {
                    ret[g[i][j] - 1]++;
                }
            }
        }
        return ret;
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
