import java.io.*;
import java.lang.*;
import java.util.*;

// 1926. Nearest Exit from Entrance in Maze

public class P1926 {
    int m, n;
    public int nearestExit(char[][] maze, int[] entrance) {
        m = maze.length;
        n = maze[0].length;
        int ex = entrance[0], ey = entrance[1];
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{ex, ey});
        int ret = 0;
        int[] dir = new int[]{-1, 0, 1, 0, -1};
        boolean[][] vis = new boolean[m][n];
        vis[ex][ey] = true;
        int step = 0;
        while (!dq.isEmpty()) {
            int sz = dq.size();
            for (int i = 0; i < sz; i++) {
                int[] cur = dq.poll();
                int curx = cur[0], cury = cur[1];
                if (curx != ex || cury != ey) {
                    boolean flag = false;
                    for (int j = 0; j < 4; j++) {
                        int nx = curx + dir[j], ny = cury + dir[j + 1];
                        if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) return step;
                }
                for (int j = 0; j < 4; j++) {
                    int nx = curx + dir[j], ny = cury + dir[j + 1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n || vis[nx][ny] || maze[nx][ny] == '+') continue;
                    vis[nx][ny] = true;
                    dq.offer(new int[]{nx, ny});
                }
            }
            step++;
            // for (int[] ee : dq) {
            //     System.out.println(Arrays.toString(ee));
            // }
        }
        return -1;
    }
}
