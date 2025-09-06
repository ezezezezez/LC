// 3628. Maximum Number of Subsequences After One Inserting

public class P3628 {
    public long numOfSubsequences(String s) {
        int n = s.length();
        long ret = 0;
        int[] prefix = new int[n + 1], suffix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (s.charAt(i) == 'L' ? 1 : 0);
        }
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + (s.charAt(i) == 'T' ? 1 : 0);
        }
        int left = 1;
        long cnt1 = 0, cnt2 = 0, cnt3 = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'C') {
                cnt1 += 1L * left * suffix[i + 1];
            }
            left = prefix[i + 1] + 1;
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == 'C') {
                cnt2 += 1L * prefix[i] * right;
            }
            right = suffix[i] + 1;
        }
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'C') {
                cnt3 += 1L * prefix[i] * suffix[i + 1];
            }
        }
        long cnt4 = 0;
        for (int i = 0; i < n - 1; i++) {
            cnt4 = Math.max(cnt4, 1L * prefix[i + 1] * suffix[i + 1]);
        }
        ret = Math.max(cnt1, cnt2);
        return Math.max(ret, cnt3 + cnt4);
    }
}
