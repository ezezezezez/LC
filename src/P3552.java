import java.util.*;

// 3552. Grid Teleportation Traversal
public class P3552 {
    public int minMoves(String[] matrix) {
        int n = matrix.length, m = matrix[0].length();
        char[][] grid = new char[n][m];
        Map<Character, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = matrix[i].charAt(j);
                if (grid[i][j] >= 'A' && grid[i][j] <= 'Z') {
                    map.computeIfAbsent(grid[i][j], kk -> new ArrayList<>()).add(new int[] {i, j});
                }
            }
        }
        Deque<int[]> dq = new ArrayDeque<>();
        Set<Character> visChar = new HashSet<>();
        boolean[][] added = new boolean[n][m];
        if (grid[0][0] >= 'A' && grid[0][0] <= 'Z') {
            visChar.add(grid[0][0]);
            for (int[] pos : map.get(grid[0][0])) {
                dq.offerLast(new int[] {pos[0], pos[1], 0});
                added[pos[0]][pos[1]] = true;
            }
        } else {
            dq.offerLast(new int[] {0, 0, 0});
            added[0][0] = true;
        }
        int[] dx = new int[]{0, 0, 0, 1, -1};
        int[] dy = new int[]{0, 1, -1, 0, 0};
        int cnt = 0;
        boolean[][] vis = new boolean[n][m];

        while (!dq.isEmpty()) {
            int[] pair = dq.pollFirst();
            int x = pair[0], y = pair[1], d = pair[2];
            // System.out.println(x + " " + y + " " + d);
            if (vis[x][y]) continue;
            vis[x][y] = true;
            if (x == n - 1 && y == m - 1) return d;

            for (int i = 1; i <= 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m|| added[nx][ny]) continue;
                if (grid[nx][ny] >= 'A' && grid[nx][ny] <= 'Z' && !visChar.contains(grid[nx][ny])) {
                    visChar.add(grid[nx][ny]);
                    for (int[] pos : map.get(grid[nx][ny])) {
                        dq.offerLast(new int[] {pos[0], pos[1], d + 1});
                        added[pos[0]][pos[1]] = true;
                    }
                    continue;
                }

                if (grid[nx][ny] == '.') {
                    dq.offerLast(new int[]{nx, ny, d + 1});
                    added[nx][ny] = true;
                }
            }
        }
        return -1;
    }
}
