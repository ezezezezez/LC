// 3674. Minimum Operations to Equalize Array

public class P3674 {
    public int minOperations(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) return 1;
        }
        return 0;
    }
}
