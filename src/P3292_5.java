import java.util.Arrays;
import java.util.Random;

// 3292. Minimum Number of Valid Strings to Form Target II
public class P3292_5 {
    public int minValidStrings(String[] words, String target) {
        int n = target.length();
        long base = 3001L;
        long[] targetHash = new long[n + 1];
        long[] targetBase = new long[n + 1];
        targetBase[0] = 1L;

        for (int i = 0; i < n; i++) {
            targetHash[i + 1] = targetHash[i] * base + target.charAt(i) - 'a' + 1;
            targetBase[i + 1] = targetBase[i] * base;
        }

        int[] maxMatch = new int[n];
        for (String word : words) {
            int m = word.length();

            long[] wordHash = new long[m + 1];
            long[] wordBase = new long[m + 1];
            wordBase[0] = 1L;

            for (int i = 0; i < m; i++) {
                wordHash[i + 1] = wordHash[i] * base + word.charAt(i) - 'a' + 1;
                wordBase[i + 1] = wordBase[i] * base;
            }

            for (int i = 0; i < n; i++) {
                char c = target.charAt(i);
                if (c != word.charAt(0)) continue;
                int lo = 1, hi = Math.min(m, n - i), t = -1;
                while (lo <= hi) {
                    int mid = lo + (hi - lo) / 2;
                    if (equalStr(targetHash, targetBase, i, wordHash, mid)) {
                        t = mid;
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
                maxMatch[i] = Math.max(maxMatch[i], t);
            }
        }

        int jumps = 0, curEnd = 0, farthest = 0;
        for (int i = 0; i < n; i++) {
            farthest = Math.max(farthest, i + maxMatch[i]);
            if (farthest >= n) break;
            if (curEnd == i) {
                if (farthest == curEnd) return -1;
                curEnd = farthest;
                jumps++;
            }
        }
        return jumps + 1;
    }

    boolean equalStr(long[] targetHash, long[] targetBase, int l, long[] wordHash, int len) {
        int r = l + len;
        long targetValue = targetHash[r] - targetHash[l] * targetBase[len];
        long wordValue = wordHash[len];
        return targetValue == wordValue;
    }
}
