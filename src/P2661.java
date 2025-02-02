import java.util.HashMap;
import java.util.Map;

// 2661. First Completely Painted Row or Column
public class P2661 {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length, n = mat[0].length;
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.put(mat[i][j], new int[] {i, j});
            }
        }
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int i = 0; i < m * n; i++) {
            int num = arr[i];
            int[] cell = map.get(num);
            int x = cell[0], y = cell[1];
            rows[x]++;
            cols[y]++;
            if (rows[x] == n) return i;
            if (cols[y] == m) return i;
        }
        return -1;
    }
}
