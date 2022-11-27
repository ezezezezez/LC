import java.util.*;
import java.io.*;
import java.lang.*;

// 1139. Largest 1-Bordered Square

public class P1139 {
    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] row = new int[m][n + 1], col = new int[m + 1][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                row[i][j + 1] = row[i][j] + grid[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                col[j + 1][i] = col[j][i] + grid[j][i];
            }
        }

        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;
                for (int len = 0; i + len < m && j + len < n; len++) {
                    int ax = i, bx = i, cx = i + len, dx = i + len;
                    int ay = j, by = j + len, cy = j, dy = j + len;
                    int cnt = len + 1;
                    if (row[bx][by + 1] - row[ax][ay] == cnt && row[cx][dy + 1] - row[cx][cy] == cnt && col[cx + 1][cy] - col[ax][ay] == cnt &&
                            col[dx + 1][dy] - col[bx][by]== cnt) {
                        ret = Math.max(ret, cnt * cnt);
                    }
                }
            }
        }

        return ret;
    }
}
