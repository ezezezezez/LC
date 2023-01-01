import java.io.*;
import java.lang.*;
import java.util.*;

// 1536. Minimum Swaps to Arrange a Binary Grid

public class P1536 {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int ret = 0;
        int[] scores = new int[n];
        for (int i = 0; i < n; i++) {
            int j = n - 1;
            while (j >= 0 && grid[i][j] == 0) j--;
            scores[i] = n - j - 1;
        }

        for (int i = 0; i < n; i++) {
            int req = n - 1 - i;
            int j = i;
            for (; j < n; j++) {
                if (scores[j] >= req) break;
            }
            if (j == n) return -1;
            for (; j > i; j--) {
                int t = scores[j];
                scores[j] = scores[j - 1];
                scores[j - 1] = t;
                ret++;
            }
        }

        return ret;
    }
}
