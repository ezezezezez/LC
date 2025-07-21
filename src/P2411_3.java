// 2411. Smallest Subarrays With Maximum Bitwise OR

public class P2411_3 {
    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if ((nums[j] | nums[i]) == nums[j]) break;
                nums[j] |= nums[i];
                ret[j] = i - j + 1;
            }
        }

        return ret;
    }
}
