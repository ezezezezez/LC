import java.math.BigInteger;
import java.util.Arrays;

// 3181. Maximum Total Reward Using Operations II
public class P3181 {
    public int maxTotalReward(int[] nums) {
        int n = nums.length;
        int[] arr = Arrays.stream(nums).distinct().sorted().toArray();
        BigInteger f = BigInteger.ONE;
        for (int num : arr) {
            BigInteger mask = BigInteger.ONE.shiftLeft(num).subtract(BigInteger.ONE);
            f = f.or(f.and(mask).shiftLeft(num));
        }
        return f.bitLength() - 1;
    }
}
