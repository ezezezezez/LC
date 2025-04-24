// 3153. Sum of Digit Differences of All Pairs
public class P3153 {
    public long sumDigitDifferences(int[] nums) {
        int n = nums.length;
        long ret = 0;
        int[][] cnt = new int[20][10];
        for (int num : nums) {
            int t = num;
            int i = 0;
            while (num > 0) {
                int d = num % 10;
                for (int j = 0; j <= 9; j++) {
                    if (j == d) continue;
                    ret += cnt[i][j];
                }
                num /= 10;
                i++;
            }
            i = 0;
            while (t > 0) {
                cnt[i][t % 10]++;
                t /= 10;
                i++;
            }
        }
        return ret;
    }
}
