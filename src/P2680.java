import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// 2680. Maximum OR
public class P2680 {
    public long maximumOr(int[] nums, int k) {
        int n = nums.length;
        long ret = 0;
        int[] cnt = new int[64];
        for (int num : nums) {
            for (int j = 0; j < 32; j++) {
                cnt[j] += ((num >> j) & 1);
            }
        }
        for (int num : nums) {
            for (int j = 0; j < 32; j++) {
                cnt[j] -= ((num >> j) & 1);
            }
            long v = ((long) num) << k;
            for (int j = 0; j < 64; j++) {
                cnt[j] += ((v >> j) & 1);
            }
            long sum = 0;
            for (int j = 0; j < 64; j++) {
                if (cnt[j] > 0) sum |= 1L << j;
            }
            ret = Math.max(ret, sum);
            for (int j = 0; j < 64; j++) {
                cnt[j] -= ((v >> j) & 1);
            }
            for (int j = 0; j < 32; j++) {
                cnt[j] += ((num >> j) & 1);
            }
        }
        return ret;
    }
}
