import java.util.Arrays;

public class P2576 {
    public int maxNumOfMarkedIndices(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ret = 0;
        for (int i = 0, j = (n + 1) / 2; j < n; i++) {
            while (j < n && nums[j] < 2 * nums[i]) j++;
            if (j == n) return ret;
            ret += 2;
            j++;
        }
        return ret;
    }
}
