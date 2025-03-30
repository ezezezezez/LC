// 3502. Minimum Cost to Reach Every Position
public class P3502 {
    public int[] minCosts(int[] cost) {
        int n = cost.length;
        int[] ret = new int[n];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (cost[i] < min) {
                ret[i] = cost[i];
                min = cost[i];
            } else {
                ret[i] = min;
            }
        }
        return ret;
    }
}
