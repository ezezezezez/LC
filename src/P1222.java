import java.util.*;
import java.io.*;
import java.lang.*;

// 1222. Queens That Can Attack the King

public class P1222 {
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        int n = 8;
        int kx = king[0], ky = king[1];
        int[][] grid = new int[n][n];
        grid[kx][ky] = 1;
        for (int[] queen : queens) grid[queen[0]][queen[1]] = 2;

        List<List<Integer>> ret = new ArrayList<>();
        int[][] prefixRow = new int[n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                prefixRow[i][j + 1] = prefixRow[i][j] + grid[i][j];
            }
        }
        int[][] prefixCol = new int[n + 1][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                prefixCol[i + 1][j] = prefixCol[i][j] + grid[i][j];
            }
        }
        int[][] prefixLeftDiag = new int[2 * n - 1][n + 1];
        int leftOffSet = n - 1;
        for (int i = -(n - 1); i <= n - 1; i++) {
            int x = Math.max(i, 0), y = x - i;
            int j = 0;
            while (x < n && y < n) {
                prefixLeftDiag[i + leftOffSet][j + 1] = prefixLeftDiag[i + leftOffSet][j] + grid[x][y];
                j++; x++; y++;
            }
        }
        int[][] prefixRightDiag = new int[2 * n - 1][n + 1];
        for (int i = 0; i < 2 * n - 1; i++) {
            int x = Math.max(i - (n - 1), 0), y = i - x;
            int j = 0;
            while (x < n && y >= 0) {
                prefixRightDiag[i][j + 1] = prefixRightDiag[i][j] + grid[x][y];
                j++; x++; y--;
            }
        }

        for (int[] queen : queens) {
            int qx = queen[0], qy = queen[1];
            if (qx == kx) {
                if (prefixRow[kx][Math.max(qy, ky)] - prefixRow[kx][Math.min(qy, ky) + 1] == 0) ret.add(List.of(qx, qy));
            } else if (qy == ky) {
                if (prefixCol[Math.max(qx, kx)][ky] - prefixCol[Math.min(qx, kx) + 1][ky] == 0) ret.add(List.of(qx, qy));
            } else if (qx - kx == qy - ky) {
                int row = qx - qy;
                int kj = Math.min(kx, ky), qj = Math.min(qx, qy);
                if (prefixLeftDiag[row + leftOffSet][Math.max(kj, qj)] - prefixLeftDiag[row + leftOffSet][Math.min(kj, qj) + 1] == 0) ret.add(List.of(qx, qy));
            } else if (qx - kx == ky - qy) {
                int row = qx + qy;
                int rky = n - 1 - ky, rqy = n - 1 - qy;
                int kj = Math.min(kx, rky), qj = Math.min(qx, rqy);
                if (prefixRightDiag[row][Math.max(kj, qj)] - prefixRightDiag[row][Math.min(kj, qj) + 1] == 0) ret.add(List.of(qx, qy));
            }
        }

        return ret;
    }
}
