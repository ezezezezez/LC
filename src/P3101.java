// 3101. Count Alternating Subarrays
public class P3101 {
    public long countAlternatingSubarrays(int[] nums) {
        int n = nums.length;
        long ret = 0, pre = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                ret += pre + 1;
                pre++;
            } else {
                ret++;
                pre = 1;
            }
        }
        return ret;
    }
}
