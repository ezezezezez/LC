import java.util.Arrays;
import java.util.List;

// 2918. Minimum Equal Sum of Two Arrays After Replacing Zeros
public class P2918 {
    public long minSum(int[] nums1, int[] nums2) {
        long ret = 0;
        int n1 = nums1.length, n2 = nums2.length;
        long sum1 = 0, sum2 = 0;
        boolean hasZero1 = false, hasZero2 = false;
        for (int num : nums1) {
            if (num == 0) {
                sum1 += 1;
                hasZero1 = true;
            } else {
                sum1 += num;
            }
        }
        for (int num : nums2) {
            if (num == 0) {
                sum2 += 1;
                hasZero2 = true;
            } else {
                sum2 += num;
            }
        }
        if (!hasZero1 && !hasZero2) return sum1 == sum2 ? sum1 : -1;
        if (hasZero1 && hasZero2) return Math.max(sum1, sum2);
        if (hasZero1) {
            if (sum1 > sum2) return -1;
            return sum2;
        } else {
            if (sum2 > sum1) return -1;
            return sum1;
        }
    }
}
