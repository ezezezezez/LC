import java.util.*;

// 3341. Find Minimum Time to Reach Last Room I

public class P3341 {
    public int minTimeToReach(int[][] moveTime) {
        int m = moveTime.length, n = moveTime[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[] {0, 0, 0});
        int[][] vis = new int[m][n];
        for (int[] row : vis) Arrays.fill(row, Integer.MAX_VALUE / 2);
        int[] dir = new int[] {0, 1, 0, -1, 0};

        while (!pq.isEmpty()) {
            int[] data = pq.poll();
            int time = data[0], x = data[1], y = data[2];
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i], ny = y + dir[i + 1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (Math.max(time, moveTime[nx][ny]) + 1 < vis[nx][ny]) {
                    vis[nx][ny] = Math.max(time, moveTime[nx][ny]) + 1;
                    if (nx == m - 1 && ny == n - 1) return vis[nx][ny];
                    pq.offer(new int[] {vis[nx][ny], nx, ny});
                }
            }
        }

        return -1;
    }
}
