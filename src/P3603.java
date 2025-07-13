// 3603. Minimum Cost Path with Alternating Directions II

import java.util.Arrays;
import java.util.PriorityQueue;

public class P3603 {
    public long minCost(int m, int n, int[][] waitCost) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[3], b[3]));
        pq.offer(new long[] {0, 0, 1, 1});
        long[][][] minCost = new long[m][n][2];
        for (long[][] a : minCost) {
            for (long[] b : a) {
                Arrays.fill(b, Long.MAX_VALUE / 2);
            }
        }
        minCost[0][0][0] = 0;
        while (!pq.isEmpty()) {
            long[] data = pq.poll();
            int x = (int) data[0], y = (int) data[1], time = (int) data[2];
            long cost = data[3];

            if (time % 2 == 1) {
                int nx = x + 1, ny = y;
                if (nx < m && cost + (nx + 1L) * (ny + 1L) < minCost[nx][ny][1]) {
                    minCost[nx][ny][1] = cost + (nx + 1L) * (ny + 1L);
                    if (nx == m - 1 && ny == n - 1) return minCost[nx][ny][1];
                    pq.offer(new long[] {nx, ny, time + 1, minCost[nx][ny][1]});
                }
                nx = x;
                ny = y + 1;
                if (ny < n && cost + (nx + 1L) * (ny + 1L) < minCost[nx][ny][1]) {
                    minCost[nx][ny][1] = cost + (nx + 1L) * (ny + 1L);
                    if (nx == m - 1 && ny == n - 1) return minCost[nx][ny][1];
                    pq.offer(new long[] {nx, ny, time + 1, minCost[nx][ny][1]});
                }
            } else {
                if (cost + waitCost[x][y] < minCost[x][y][0]) {
                    minCost[x][y][0] = cost + waitCost[x][y];
                    pq.offer(new long[] {x, y, time + 1, minCost[x][y][0]});
                }
            }
        }
        return -1;
    }
}
