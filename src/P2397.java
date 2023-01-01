import java.io.*;
import java.lang.*;
import java.util.*;

// 2397. Maximum Rows Covered by Columns

public class P2397 {
    public int maximumRows(int[][] matrix, int numSelect) {
        int m = matrix.length, n = matrix[0].length;
        int ret = 0;
        for (int i = 0; i < (1 << n); i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (((1 << j) & i) > 0) {
                    cnt++;
                }
            }
            if (cnt != numSelect) continue;
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if (((1 << j) & i) > 0) {
                    set.add(j);
                }
            }
            int sum = 0;
            for (int r = 0; r < m; r++) {
                boolean flag = true;
                for (int c = 0; c < n; c++) {
                    if (matrix[r][c] == 1 && !set.contains(c)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) sum++;
            }
            ret = Math.max(ret, sum);
        }
        return ret;
    }
}
