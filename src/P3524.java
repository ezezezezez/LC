import java.util.ArrayDeque;
import java.util.Deque;

// 3524. Find X Value of Array I
public class P3524 {
    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
        long[][] f = new long[n][k];
        f[0][nums[0] % k]++;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            f[i][num % k]++;
            for (int j = 0; j < k; j++) {
                long t = (long) j * num % k;
                f[i][(int) t] += f[i - 1][j];
            }
        }
        long[] ret = new long[k];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                ret[j] += f[i][j];
            }
        }
        return ret;
    }
}
