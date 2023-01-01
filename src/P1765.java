import java.io.*;
import java.lang.*;
import java.util.*;

// 1765. Map of Highest Peak

public class P1765 {
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;
        int[][] ret = new int[m][n];
        Deque<int[]> dq = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    dq.offer(new int[]{i, j, 0});
                }
            }
        }
        int[] dir = new int[]{-1, 0, 1, 0, -1};
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int x = cur[0], y = cur[1], level = cur[2];
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i], ny = y + dir[i + 1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || isWater[nx][ny] == 1 || ret[nx][ny] > 0) continue;
                ret[nx][ny] = level + 1;
                dq.offer(new int[]{nx, ny, level + 1});
            }
        }
        return ret;
    }
}
