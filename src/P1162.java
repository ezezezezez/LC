import java.util.*;
import java.io.*;
import java.lang.*;

// 1161. Maximum Level Sum of a Binary Tree

public class P1162 {
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        int landCnt = 0, waterCnt = 0;
        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][] vis = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) waterCnt++;
                else {
                    landCnt++;
                    dq.offer(new int[]{i, j});
                    vis[i][j] = true;
                }
            }
        }
        if (landCnt == 0 || waterCnt == 0) return -1;

        int[] dir = new int[]{-1, 0, 1, 0, -1};
        int step = 0;
        while (!dq.isEmpty()) {
            int sz = dq.size();
            for (int i = 0; i < sz; i++) {
                int[] node = dq.poll();
                int x = node[0], y = node[1];
                for (int j = 0; j < 4; j++) {
                    int nx = x + dir[j], ny = y + dir[j + 1];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                    if (vis[nx][ny]) continue;
                    vis[nx][ny] = true;
                    dq.offer(new int[]{nx, ny});
                }
            }
            step++;
        }

        return step - 1;
    }
}
