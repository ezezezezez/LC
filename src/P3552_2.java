import java.util.*;

// 3552. Grid Teleportation Traversal
public class P3552_2 {
    public int minMoves(String[] matrix) {
        int m = matrix.length, n = matrix[0].length();
        char[][] grid = new char[m][n];
        Map<Character, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = matrix[i].charAt(j);
                if (grid[i][j] >= 'A' && grid[i][j] <= 'Z') {
                    map.computeIfAbsent(grid[i][j], kk -> new ArrayList<>()).add(new int[] {i, j});
                }
            }
        }
        Deque<int[]> dq = new ArrayDeque<>();
        Set<Character> visChar = new HashSet<>();
        boolean[][] added = new boolean[m][n];
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
        int[] dir = new int[]{0, 1, 0, -1, 0};

        while (!dq.isEmpty()) {
            int[] pair = dq.pollFirst();
            int x = pair[0], y = pair[1], d = pair[2];
            if (x == m - 1 && y == n - 1) return d;

            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i], ny = y + dir[i + 1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n|| added[nx][ny]) continue;
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
