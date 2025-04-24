import java.util.List;

// 3148. Maximum Difference Score in a Grid
public class P3148 {
    public int maxScore(List<List<Integer>> grid) {
        int m = grid.size(), n = grid.get(0).size();
        int ret = Integer.MIN_VALUE;
        int[] f = new int[n];
        int[][] see = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            int rowMx = Integer.MIN_VALUE / 2;
            for (int j = n - 1; j >= 0; j--) {
                if (j == n - 1) {
                    if (i == m - 1) {
                        rowMx = grid.get(i).get(j);
                        f[j] = grid.get(i).get(j);
                        continue;
                    }
                    int curMx = f[j] - grid.get(i).get(j);
                    ret = Math.max(ret, curMx);
                    rowMx = Math.max(grid.get(i).get(j), curMx + grid.get(i).get(j));
                    f[j] = Math.max(f[j], Math.max(grid.get(i).get(j), curMx + grid.get(i).get(j)));
                } else {
                    int curMx = Math.max(rowMx, f[j]) - grid.get(i).get(j);
                    ret = Math.max(ret, curMx);
                    rowMx = Math.max(rowMx, Math.max(grid.get(i).get(j), curMx + grid.get(i).get(j)));
                    f[j] = Math.max(f[j], Math.max(grid.get(i).get(j), curMx + grid.get(i).get(j)));
                    see[i][j] = curMx;
                }
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
