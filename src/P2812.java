import java.util.*;

// 2812. Find the Safest Path in a Grid
public class P2812 {
    int[] dir = new int[] {0, 1, 0, -1, 0};
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        Queue<int[]> q = new ArrayDeque<>();
        int[][] dist = new int[n][n];
        for (int[] row : dist) Arrays.fill(row, -1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    q.offer(new int[] {i, j});
                    dist[i][j] = 0;
                }
            }
        }
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int x = node[0], y = node[1];
            int d = dist[x][y];
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i], ny = y + dir[i + 1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (dist[nx][ny] != -1) continue;
                dist[nx][ny] = d + 1;
                q.offer(new int[] {nx, ny});
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[2], a[2]));
        pq.offer(new int[] {0, 0, dist[0][0]});
        int[][] graph = new int[n][n];
        for (int[] row : graph) Arrays.fill(row, -1);
        graph[0][0] = dist[0][0];
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int x = node[0], y = node[1], d = node[2];
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i], ny = y + dir[i + 1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (graph[nx][ny] != -1) continue;
                graph[nx][ny] = Math.min(graph[x][y], dist[nx][ny]);
                if (nx == n - 1 && ny == n - 1) return graph[nx][ny];
                pq.offer(new int[] {nx, ny, graph[nx][ny]});
            }
        }
        return graph[n - 1][n - 1];
    }
}
