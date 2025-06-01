// 3560. Find Minimum Log Transportation Cost
public class P3560 {
    public long minCuttingCost(int n, int m, int k) {
        if (n <= k && m <= k) return 0;
        if (n > k) {
            return 1L * k * (n - k);
        } else {
            return 1L * k * (m - k);
        }
    }
}
