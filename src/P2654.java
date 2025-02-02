// 2654. Minimum Number of Operations to Make All Array Elements Equal to 1
public class P2654 {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int oneCnt = 0;
        for (int num : nums) {
            if (num == 1) oneCnt++;
        }
        if (oneCnt > 0) return n - oneCnt;
        int ret = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int g = nums[i];
            for (int j = i - 1; j >= 0; j--) {
                g = gcd(g, nums[j]);
                if (g == 1) {
                    ret = Math.min(ret, i - j + n - 1);
                    break;
                }
            }
        }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }

    int gcd(int a, int b) {
        return b > 0 ? gcd(b, a % b) : a;
    }
}
