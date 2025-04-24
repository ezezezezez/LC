import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 3181. Maximum Total Reward Using Operations II
public class P3181_2 {
    public int maxTotalReward(int[] nums) {
        int n = nums.length;
        int[] arr = Arrays.stream(nums).distinct().sorted().toArray();
        int mx = 0;
        for (int num : arr) {
            mx = Math.max(mx, num);
        }
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            if (num == mx - 1) return 2 * mx - 1;
            if (set.contains(mx - 1 - num)) return 2 * mx - 1;
            set.add(num);
        }
        BigInteger f = BigInteger.ONE;
        for (int num : arr) {
            BigInteger mask = BigInteger.ONE.shiftLeft(num).subtract(BigInteger.ONE);
            f = f.or(f.and(mask).shiftLeft(num));
        }
        return f.bitLength() - 1;
    }
}
