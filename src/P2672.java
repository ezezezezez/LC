import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 2672. Number of Adjacent Elements With the Same Color
public class P2672 {
    public int[] colorTheArray(int n, int[][] queries) {
        int[] colors = new int[n];
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            int idx = query[0], color = query[1];
            if (i == 0) {
                colors[idx] = color;
                continue;
            }
            ans[i] = ans[i - 1];
            int preCnt = 0;
            if (idx - 1 >= 0 && colors[idx - 1] != 0 && colors[idx] != 0 && colors[idx - 1] == colors[idx]) {
                preCnt++;
            }
            if (idx + 1 < n && colors[idx + 1] != 0 && colors[idx] != 0 && colors[idx + 1] == colors[idx]) {
                preCnt++;
            }
            ans[i] -= preCnt;
            if (colors[idx] != 0 && color == colors[idx]) {
                ans[i] += preCnt;
            } else if (colors[idx] == 0) {
                if (idx - 1 >= 0 && colors[idx - 1] != 0 && colors[idx - 1] == color) {
                    ans[i]++;
                }
                if (idx + 1 < n && colors[idx + 1] != 0 && colors[idx + 1] == color) {
                    ans[i]++;
                }
                colors[idx] = color;
            } else {
                colors[idx] = color;
                if (idx - 1 >= 0 && colors[idx - 1] != 0 && colors[idx - 1] == color) {
                    ans[i]++;
                }
                if (idx + 1 < n && colors[idx + 1] != 0 && colors[idx + 1] == color) {
                    ans[i]++;
                }
            }
        }
        return ans;
    }
}
