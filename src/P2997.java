import java.util.HashMap;
import java.util.Map;

// 2997. Minimum Number of Operations to Make Array XOR Equal to K
public class P2997 {
    public int minOperations(int[] nums, int k) {
        int n = nums.length;
        int x = 0;
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                x = x ^ ((1 << i) & num);
            }
        }
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            ret += (x & (1 << i)) == (k & (1 << i)) ? 0 : 1;
        }
        return ret;
    }
}
