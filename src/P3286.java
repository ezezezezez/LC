import java.util.List;

// 3286. Find a Safe Walk Through a Grid
public class P3286 {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size(), n = grid.get(0).size();
        boolean[][] vis = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[2], a[2]));;
        health = grid.get(0).get(0) == 1 ? health - 1 : health;
        if (health <= 0) return false;
        pq.offer(new int[] {0, 0, health});
        int[] dir = new int[] {1, 0, -1, 0, 1};
        int[][] d = new int[m][n];
        for (int[] row : d) Arrays.fill(row, -1);
        d[0][0] = health;
        while (!pq.isEmpty()) {
            int[] tuple = pq.poll();
            int x = tuple[0], y = tuple[1], h = tuple[2];
            if (h <= 0) return false;
            if (x == m - 1 && y == n - 1) return true;
            if (vis[x][y]) continue;
            vis[x][y] = true;
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i], ny = y + dir[i + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    int v = grid.get(nx).get(ny);
                    int nh = h - (v == 1 ? 1 : 0);
                    if (d[nx][ny] == -1 || d[nx][ny] < nh) {
                        if (nx == m - 1 && ny == n - 1 && nh >= 1) return true;
                        d[nx][ny] = nh;
                        pq.offer(new int[] {nx, ny, nh});
                    }
                }
            }
        }
        return false;
    }
}
