import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 3579. Minimum Steps to Convert String with Operations
public class P3579 {
    public int minOperations(String word1, String word2) {
        int n = word1.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;

        char[] w1 = word1.toCharArray(), w2 = word2.toCharArray();

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                // if (i == n) System.out.println(calc(w1, w2, j, i - 1));
                dp[i] = Math.min(dp[i], dp[j] + calc(w1, w2, j, i - 1));
            }
        }

        // System.out.println(Arrays.toString(dp));

        return dp[n];
    }

    int calc(char[] w1, char[] w2, int l, int r) {
        List<Integer> misIdx = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            if (w1[i] != w2[i]) {
                misIdx.add(i);
            }
        }
        if (misIdx.isEmpty()) return 0;
        int maxSwap = 0;
        boolean[] used = new boolean[misIdx.size()];
        for (int i = 0; i < misIdx.size(); i++) {
            if (used[i]) continue;
            for (int j = i + 1; j < misIdx.size(); j++) {
                if (used[j]) continue;
                int ii = misIdx.get(i), jj = misIdx.get(j);
                if (w1[ii] == w2[jj] && w1[jj] == w2[ii]) {
                    maxSwap++;
                    used[i] = used[j] = true;
                    break;
                }
            }
        }
        int cost1 = misIdx.size() - maxSwap;

        List<Integer> misIdxRev = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            int j = r - (i - l);
            if (w1[i] != w2[j]) {
                misIdxRev.add(i);
            }
        }
        if (misIdxRev.isEmpty()) return 1;
        int maxSwapRev = 0;
        boolean[] usedRev = new boolean[misIdxRev.size()];
        for (int i = 0; i < misIdxRev.size(); i++) {
            if (usedRev[i]) continue;
            for (int j = i + 1; j < misIdxRev.size(); j++) {
                if (usedRev[j]) continue;
                int ii = misIdxRev.get(i), jj = misIdxRev.get(j);
                int iir = r - (ii - l), jjr = r - (jj - l);
                if (w1[ii] == w2[jjr] && w1[jj] == w2[iir]) {
                    maxSwapRev++;
                    usedRev[i] = usedRev[j] = true;
                    break;
                }
            }
        }
        int cost2 = misIdxRev.size() - maxSwapRev;
        // if (r - l + 1 == w1.length) System.out.println(cost1 + " " + cost2 + " " + misIdxRev + " " + maxSwapRev);
        return Math.min(cost1, 1 + cost2);
    }
}
