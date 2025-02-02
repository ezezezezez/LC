import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P2592 {
    public int maximizeGreatness(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ret = 0;
        for (int i = 0, j = 1; j < n; j++) {
            while (j < n && nums[j] == nums[i]) j++;
            if (j == n) return ret;
            ret++;
            i++;
        }
        return ret;
    }
}
