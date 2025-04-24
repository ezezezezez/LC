import java.util.Arrays;
import java.util.List;

// 3148. Maximum Difference Score in a Grid
public class P3148_2 {
    public int maxScore(List<List<Integer>> grid) {
        int m = grid.size(), n = grid.get(0).size();
        int[][] f = new int[m + 1][n + 1];
        for (int[] row : f) Arrays.fill(row, Integer.MAX_VALUE / 2);
        int ret = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    f[i + 1][j + 1] = grid.get(i).get(j);
                    continue;
                }
                ret = Math.max(ret, grid.get(i).get(j) - Math.min(f[i][j + 1], f[i + 1][j]));
                f[i + 1][j + 1] = Math.min(grid.get(i).get(j), Math.min(f[i][j + 1], f[i + 1][j]));
            }
        }
        // printGrid2D(see);
        return ret;
    }

    void printGrid2D(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
