// 3568. Minimum Moves to Clean the Classroom

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class P3568 {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n  = classroom[0].length();
        char[][] grid = new char[m][];
        for (int i = 0; i < m; i++) grid[i] = classroom[i].toCharArray();

        Deque<int[]> dq = new ArrayDeque<>();

        Map<Integer, Integer> litters = new HashMap<>();

        int litter = 0, sx = -1, sy = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S') {
                    sx = i;
                    sy = j;
                } else if (grid[i][j] == 'L') {
                    litter++;
                    litters.put((i << 8) | j, litters.size());
                }
            }
        }

        if (litter == 0) return 0;

        boolean[][][][] vis = new boolean[m][n][energy + 1][1 << litter];
        dq.offer(new int[] {0, sx, sy, energy, (1 << litter) - 1});
        vis[sx][sy][energy][litter] = true;

        int[] dir = new int[] {0, 1, 0, -1, 0};

        while (!dq.isEmpty()) {
            int[] node = dq.poll();
            int move = node[0], x = node[1], y = node[2], e = node[3], lmask = node[4];
            // System.out.println(Arrays.toString(node));

            // next state is after all processing in that next cell (recharged energy)
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i], ny = y + dir[i + 1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] == 'X' || e == 0) continue;

                int ne = e - 1;
                if (grid[nx][ny] == 'R') ne = energy;

                int nlmask = lmask;
                if (grid[nx][ny] == 'L' && (lmask & (1 << litters.get((nx << 8) | ny))) > 0) {
                    nlmask -= 1 << litters.get((nx << 8) | ny);
                }
                if (nlmask == 0) return move + 1;

                if (vis[nx][ny][ne][nlmask]) continue;
                vis[nx][ny][ne][nlmask] = true;
                dq.offer(new int[] {move + 1, nx, ny, ne, nlmask});
            }
        }

        return -1;
    }
}
