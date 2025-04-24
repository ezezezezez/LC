import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 3191. Minimum Operations to Make Binary Array Elements Equal to One I
public class P3191 {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ret = 0;
        for (int i = 0; i < n - 2; i++) {
            int num = nums[i];
            if (num == 0) {
                nums[i + 1] ^= 1;
                nums[i + 2] ^= 1;
                ret++;
            }
        }
        if (nums[n - 2] == 0 || nums[n - 1] == 0) return -1;
        return ret;
    }
}
