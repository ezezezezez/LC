import java.util.Arrays;

// 673. Number of Longest Increasing Subsequence
public class P673 {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] len = new int[n];
        Arrays.fill(len, 1);
        int[] cnt = new int[n];
        Arrays.fill(cnt, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (len[j] + 1 > len[i]) {
                        len[i] = len[j] + 1;
                        cnt[i] = cnt[j];
                    } else if (len[j] + 1 == len[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }
        }

        int mx = 0;
        for (int l : len) mx = Math.max(mx, l);

        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (len[i] == mx) {
                ret += cnt[i];
            }
        }
        return ret;
    }
}
