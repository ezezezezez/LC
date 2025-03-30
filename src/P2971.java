import java.util.Arrays;

// 2971. Find Polygon With the Largest Perimeter
public class P2971 {
    public long largestPerimeter(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        long sum = 0;
        for (int num : nums) sum += num;
        for (int i = n - 1; i >= 2; i--) {
            if (sum - nums[i] > nums[i]) return sum;
            sum -= nums[i];
        }
        return -1;
    }
}
