// 3574. Maximize Subarray GCD Score
public class P3574 {
    public long maxGCDScore(int[] nums, int k) {
        int n = nums.length;
        int[] e = new int[n];
        int[] odd = new int[n];
        for (int i = 0; i < n; i++) {
            int x = nums[i], c = 0;
            while ((x & 1) == 0) {
                x >>= 1;
                c++;
            }
            odd[i] = x;
            e[i] = c;
        }

        long ret = 0;
        for (int i = 0; i < n; i++) {
            int[] minCnt = new int[32];
            int minPower = 32;
            int g = 0;
            for (int j = i; j < n; j++) {
                if (g == 0) g = odd[j];
                else g = gcd(g, odd[j]);

                minCnt[e[j]]++;
                if (e[j] < minPower) minPower = e[j];

                int len = j - i + 1;
                long G = (long) g << minPower;
                if (k >= minCnt[minPower]) G <<= 1;
                ret = Math.max(ret, 1L * len * G);
            }
        }

        return ret;
    }

    int gcd (int a, int b) {
        return b > 0 ? gcd (b, a % b) : a;
    }
}
