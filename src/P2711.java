import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 2711. Difference of Number of Distinct Values on Diagonals
public class P2711 {
    public int[][] differenceOfDistinctValues(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Set<Integer> leftAbove = new HashSet<>();
                Set<Integer> rightBelow = new HashSet<>();
                for (int k = 1; i - k >= 0 && j - k >= 0; k++) {
                    leftAbove.add(grid[i - k][j - k]);
                }
                for (int k = 1; i + k < m && j + k < n; k++) {
                    rightBelow.add(grid[i + k][j + k]);
                }
                ans[i][j] = Math.abs(leftAbove.size() - rightBelow.size());
            }
        }
        return ans;
    }
}
