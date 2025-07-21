// 3574. Maximize Subarray GCD Score
public class P3574_2 {
    public long maxGCDScore(int[] nums, int k) {
        int n = nums.length;
        long ret = 0;
        for (int i = 0; i < n; i++) {
            int g = 0;
            int[] cnt = new int[31];
            int minPower = -1;
            for (int j = i; j >= 0; j--) {
                int num = nums[j];
                g = gcd(g, num);
                int x = 0;
                while (num % (1 << x) == 0) x++;
                cnt[x - 1]++;
                if (minPower == -1 || x - 1 < minPower) {
                    minPower = x - 1;
                }
                int len = i - j + 1;
                int increase = cnt[minPower] <= k ? minPower + 1 : minPower;
                ret = Math.max(ret, 1L * len * g / (1 << minPower) * (1 << increase));
                // System.out.println(i + " " + j + " " + minPower + " " + increase);
                // System.out.println(Arrays.toString(cnt));
                // System.out.println(g + " " + ret + " " + minPower);
            }
        }

        return ret;
    }

    int gcd(int a, int b) {
        return b > 0 ? gcd(b, a % b) : a;
    }
}
