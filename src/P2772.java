// 2772. Apply Operations to Make All Array Elements Equal to Zero
public class P2772 {
    public boolean checkArray(int[] nums, int k) {
        int n = nums.length;
        long[] prefix = new long[n];
        long change = 0;
        for (int i = 0; i < n; i++) {
            change += prefix[i];
            long num = nums[i] + change;
            if (num < 0) return false;
            if (num > 0 && i > n - k) return false;
            change -= num;
            if (i + k < n) prefix[i + k] += num;
        }
        return true;
    }
}
