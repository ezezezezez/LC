import java.io.*;
import java.lang.*;
import java.util.*;

// 1706. Where Will the Ball Fall

public class P1706 {
    public int[] findBall(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Deque<int[]> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            dq.offer(new int[]{i, -1, i});
        }
        int[] ret = new int[n];
        while (!dq.isEmpty()) {
            int sz = dq.size();
            for (int i = 0; i < sz; i++) {
                int[] cur = dq.poll();
                int id = cur[0], row = cur[1], col = cur[2];
                int nxtRow = row + 1;
                if (nxtRow == m) {
                    ret[id] = col;
                    continue;
                }
                if (grid[nxtRow][col] == 1) {
                    if (col + 1 == n || grid[nxtRow][col + 1] == -1) {
                        ret[id] = -1;
                        continue;
                    }
                    dq.offer(new int[]{id, nxtRow, col + 1});
                } else {
                    if (col - 1 < 0 || grid[nxtRow][col - 1] == 1) {
                        ret[id] = -1;
                        continue;
                    }
                    dq.offer(new int[]{id, nxtRow, col - 1});
                }
            }
        }
        return ret;
    }
}
