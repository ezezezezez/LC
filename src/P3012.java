import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 3012. Minimize Length of Array Using Operations
public class P3012 {
    public int minimumArrayLength(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 1; i < n; i++) {
            if (nums[i] % nums[0] != 0) return 1;
        }
        int j = -1;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[0]) {
                j = i;
                break;
            }
        }
        if (j == -1) {
            return (n + 1) / 2;
        }
        return (j + 1) / 2;
    }
}
