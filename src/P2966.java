import java.util.Arrays;

// 2966. Divide Array Into Arrays With Max Difference
public class P2966 {
    public int[][] divideArray(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        // System.out.println(Arrays.toString(nums));
        int[][] ret = new int[n / 3][3];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i + 2] - nums[i] > k) return new int[0][];
            ret[idx][0] = nums[i];
            ret[idx][1] = nums[i + 1];
            ret[idx][2] = nums[i + 2];
            i += 2;
            idx++;
        }
        return ret;
    }
}
