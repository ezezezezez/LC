import java.util.List;

// 2865. Beautiful Towers I
public class P2865 {
    public long maximumSumOfHeights(int[] heights) {
        int n = heights.length;
        long ret = 0, sum = 0;
        for (int h : heights) sum += h;
        for (int i = 0; i < n; i++) {
            int pre = heights[i];
            long cnt = 0;
            for (int j = i + 1; j < n; j++) {
                if (heights[j] > pre) {
                    cnt += heights[j] - pre;
                }
                pre = Math.min(pre, heights[j]);
            }
            pre = heights[i];
            for (int j = i - 1; j >= 0; j--) {
                if (heights[j] > pre) {
                    cnt += heights[j] - pre;
                }
                pre = Math.min(pre, heights[j]);
            }
            ret = Math.max(ret, sum - cnt);
        }
        return ret;
    }
}
