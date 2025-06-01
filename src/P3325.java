// 3325. Count Substrings With K-Frequency Characters I

public class P3325 {
    public int numberOfSubstrings(String s, int k) {
        int n = s.length();
        int ret = 0;
        int[][] prefix = new int[26][n + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                prefix[j][i + 1] = prefix[j][i];
            }
            prefix[s.charAt(i) - 'a'][i + 1] += 1;
        }

        for (int i = 1; i <= n; i++) {
            int mx = Integer.MIN_VALUE;
            for (int j = 0; j < 26; j++) {
                int d = prefix[j][i] - k;
                int idx = upperBound(prefix[j], 0, n + 1, d) - 1;
                mx = Math.max(mx, idx);
            }
            if (mx != Integer.MIN_VALUE) ret += mx + 1;
        }

        return ret;
    }

    int upperBound(int[] nums, int lo, int hi, int val) {
        lo--;
        while (hi - lo > 1) {
            int mid = (lo + hi) >> 1;
            if (nums[mid] > val) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return hi;
    }
}
