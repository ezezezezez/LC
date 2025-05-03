import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 3529. Count Cells in Overlapping Horizontal and Vertical Substrings
public class P3529 {
    public int countCells(char[][] grid, String pattern) {
        int m = grid.length, n = grid[0].length;
        boolean[][] b1 = new boolean[m][n];
        boolean[][] b2 = new boolean[m][n];
        StringBuilder sb1 = new StringBuilder(m * n);
        StringBuilder sb2 = new StringBuilder(m * n);
        for (char[] row : grid) {
            for (char c : row) {
                sb1.append(c);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb2.append(grid[j][i]);
            }
        }
        List<Integer> pos1 = search(sb1.toString(), pattern);
        List<Integer> pos2 = search(sb2.toString(), pattern);
        // System.out.println(pos1);
        // System.out.println(pos2);
        int len = pattern.length();
        int s1 = 0;
        for (int idx : pos1) {
            s1 = Math.max(s1, idx);
            int p = s1;
            for (; p < idx + len && p < m * n; p++) {
                int r = p / n, c = p % n;
                b1[r][c] = true;
            }
            s1 = p;
        }
        int s2 = 0;
        for (int idx : pos2) {
            s2 = Math.max(s2, idx);
            int p = s2;
            for (; p < idx + len && p < m * n; p++) {
                int r = p % m, c = p / m;
                b2[r][c] = true;
            }
            s2 = p;
        }
        // printGrid2D(b1);
        // printGrid2D(b2);
        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (b1[i][j] && b2[i][j]) ret++;
            }
        }
        return ret;
    }

    void printGrid2D(boolean[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    List<Integer> search(String text, String pattern) {
        List<Integer> positions = new ArrayList<>();
        int[] maxMatchLengths = calculateMaxMatchLengths(pattern);
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            while (count > 0 && pattern.charAt(count) != text.charAt(i)) {
                count = maxMatchLengths[count - 1];
            }
            if (pattern.charAt(count) == text.charAt(i)) {
                count++;
            }
            if (count == pattern.length()) {
                positions.add(i - pattern.length() + 1);
                count = maxMatchLengths[count - 1];
            }
        }
        return positions;
    }

    int[] calculateMaxMatchLengths(String pattern) {
        int[] maxMatchLengths = new int[pattern.length()];
        for (int i = 1; i < pattern.length(); i++) {
            int j = maxMatchLengths[i - 1];
            while (j > 0 && pattern.charAt(j) != pattern.charAt(i)) {
                j = maxMatchLengths[j - 1];
            }
            if (pattern.charAt(j) == pattern.charAt(i)) {
                j++;
            }
            maxMatchLengths[i] = j;
        }
        return maxMatchLengths;
    }
}
