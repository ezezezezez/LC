import java.io.*;
import java.lang.*;
import java.util.*;

// 2257. Count Unguarded Cells in the Grid

public class P2257 {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];
        for (int[] wall : walls) {
            int x = wall[0], y = wall[1];
            grid[x][y] = 1;
        }
        for (int[] guard : guards) {
            int x = guard[0], y = guard[1];
            grid[x][y] = 2;
        }
        for (int i = 0; i < m; i++) {
            int pre = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    int k = j - 1;
                    while (k >= 0 && grid[i][k] == 0) {
                        grid[i][k] = 3;
                        k--;
                    }
                    pre = 2;
                } else if (grid[i][j] == 1) {
                    pre = 0;
                } else {
                    if (pre == 2) {
                        grid[i][j] = 3;
                    }
                }
            }
        }
        for (int j = 0; j < n; j++) {
            int pre = 0;
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 2) {
                    int k = i - 1;
                    while (k >= 0 && (grid[k][j] == 0 || grid[k][j] == 3)) {
                        grid[k][j] = 3;
                        k--;
                    }
                    pre = 2;
                } else if (grid[i][j] == 1) {
                    pre = 0;
                } else {
                    if (pre == 2) {
                        grid[i][j] = 3;
                    }
                }
            }
        }
        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) ret++;
            }
        }
        return ret;
    }

    public int countUnguarded2(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];
        int occ = 0;
        for (int[] wall : walls) {
            int x = wall[0], y = wall[1];
            grid[x][y] = 1;
            occ++;
        }
        for (int[] guard : guards) {
            int x = guard[0], y = guard[1];
            grid[x][y] = 2;
            occ++;
        }
        int[] dir = new int[]{-1, 0, 1, 0, -1};
        for (int[] guard : guards) {
            int x = guard[0], y = guard[1];
            for (int j = 0; j < 4; j++) {
                int nx = x + dir[j], ny = y + dir[j + 1];
                while (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] != 1 && grid[nx][ny] != 2) {
                    if (grid[nx][ny] == 0) occ++;
                    grid[nx][ny] = 3;
                    nx += dir[j];
                    ny += dir[j + 1];
                }
            }
        }
        return m * n - occ;
    }
}
