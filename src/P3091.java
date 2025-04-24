import java.util.Arrays;

// 3091. Apply Operations to Make Sum of Array Greater Than or Equal to k
public class P3091 {
    public int minOperations(int k) {
        if (k == 1) return 0;
        int ret = Integer.MAX_VALUE;
        for (int i = 1; i <= k; i++) {
            ret = Math.min(ret, (k - 1) / i + i - 1);
        }
        return ret;
    }
}
