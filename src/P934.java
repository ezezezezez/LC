import java.util.*;
import java.io.*;
import java.lang.*;

// 934. Shortest Bridge

public class P934 {
    Set<Integer> i1 = new HashSet<>();
    Set<Integer> i2 = new HashSet<>();
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        int landCnt = 1;
        boolean[][] pointVis = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 || pointVis[i][j]) continue;
                dfs(grid, i, j, landCnt++, pointVis);
            }
        }
        // System.out.println(i1);
        // System.out.println(i2);

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] vis = new boolean[n][n];
        for (int point : i1) {
            int[] p = decode(point, n);
            int x = p[0], y = p[1];
            boolean flag = false;
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i], ny = y + dir[i + 1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (grid[nx][ny] == 0) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                vis[p[0]][p[1]] = true;
                q.offer(p);
            }
        }

        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int[] p = q.poll();
                int x = p[0], y = p[1];
                for (int j = 0; j < 4; j++) {
                    int nx = x + dir[j], ny = y + dir[j + 1];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                    if (vis[nx][ny]) continue;
                    if (i2.contains(encode(nx, ny, n))) return step;
                    q.offer(new int[]{nx, ny});
                    vis[nx][ny] = true;
                }
            }
            step++;
        }

        return -1;
    }

    void dfs(int[][] grid, int x, int y, int landCnt, boolean[][] vis) {
        int n = grid.length;
        if (x < 0 || x >= n || y < 0 || y >= n || vis[x][y] || grid[x][y] == 0) return;
        vis[x][y] = true;
        if (landCnt == 1) i1.add(encode(x, y, n));
        else i2.add(encode(x, y, n));

        dfs(grid, x - 1, y, landCnt, vis);
        dfs(grid, x + 1, y, landCnt, vis);
        dfs(grid, x, y - 1, landCnt, vis);
        dfs(grid, x, y + 1, landCnt, vis);
    }

    int encode(int x, int y, int n) {
        return x * n + y;
    }

    int[] decode(int num, int n) {
        return new int[]{num / n, num % n};
    }
}
