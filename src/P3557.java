import java.util.*;

// 3557. Find Maximum Number of Non Intersecting Substrings
public class P3557 {
    public int maxSubstrings(String word) {
        int n = word.length();
        int[] dp = new int[n + 1];
        List<Integer>[] indices = new List[26];
        Arrays.setAll(indices, kk -> new ArrayList<>());
        for (int i = 0; i < n; i++) {
            indices[word.charAt(i) - 'a'].add(i);
        }

        for (int i = 3; i < n; i++) {
            dp[i + 1] = dp[i];
            int x = word.charAt(i) - 'a';
            List<Integer> list = indices[x];
            int y = lowerBound(list, 0, list.size(), i);
            int z = y - 1;
            while (z >= 0 && i - list.get(z) < 3) z--;
            if (z >= 0) {
                dp[i + 1] = Math.max(dp[i], 1 + dp[list.get(z)]);
            }
        }
        // System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    int lowerBound(List<Integer> nums, int lo, int hi, int val) {
        lo--;
        while (hi - lo > 1) {
            int mid = (lo + hi) >> 1;
            if (nums.get(mid) >= val) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return hi;
    }
}
