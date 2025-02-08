import java.util.Arrays;

// 2740. Find the Value of the Partition
public class P2740 {
    public int findValueOfPartition(int[] nums) {
        int ret = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) ret = Math.min(ret, nums[i] - nums[i - 1]);
        return ret;
    }
}
