// 3653. XOR After Range Multiplication Queries I

public class P3653 {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length, qlen = queries.length;
        int mod = 1000000007;
        for (int i = 0; i < qlen; i++) {
            int[] q = queries[i];
            int l = q[0], r = q[1], k = q[2], v = q[3];
            for (int j = l; j <= r; j += k) {
                nums[j] = (int) ((1L * nums[j] * v) % mod);
            }
        }
        int ret = 0;
        for (int num : nums) ret ^= num;
        return ret;
    }
}
