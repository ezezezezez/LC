import java.util.Arrays;
import java.util.Random;

// 3292. Minimum Number of Valid Strings to Form Target II
public class P3292_4 {
    final long BASE;

    public P3292_4() {
        long low = 100_000L;
        long high = 10_000_000L;
        Random rng = new Random(System.nanoTime());
        long range = high - low;
        long candidate = low + Math.abs(rng.nextLong()) % range;
        if ((candidate & 1) == 0) {
            candidate++;
        }
        BASE = candidate;
    }

    public int minValidStrings(String[] words, String target) {
        int n = target.length();
        int[] globalMax = new int[n];
        Arrays.fill(globalMax, 0);

        long[] targetHash = new long[n + 1];
        long[] targetPow  = new long[n + 1];
        targetPow[0] = 1;
        for (int i = 0; i < n; i++) {
            targetHash[i + 1] = targetHash[i] * BASE + (target.charAt(i) - 'a' + 1);
            targetPow[i + 1] = targetPow[i] * BASE;
        }

        for (String word : words) {
            int m = word.length();
            if (target.indexOf(word.charAt(0)) == -1) continue;

            long[] wordHash = new long[m + 1];
            long[] wordPow = new long[m + 1];
            wordPow[0] = 1;
            for (int i = 0; i < m; i++) {
                wordHash[i + 1] = wordHash[i] * BASE + (word.charAt(i) - 'a' + 1);
                wordPow[i + 1] = wordPow[i] * BASE;
            }

            for (int i = 0; i < n; i++) {
                if (target.charAt(i) != word.charAt(0)) continue;
                int hi = Math.min(m, n - i);
                int lo = 1, best = 0;
                while (lo <= hi) {
                    int mid = lo + (hi - lo) / 2;
                    if (equalSubstr(targetHash, targetPow, i, i + mid, wordHash, mid)) {
                        best = mid;
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
                globalMax[i] = Math.max(globalMax[i], best);
            }
        }

        int jumps = 0, currentEnd = 0, farthest = 0;
        for (int i = 0; i < n; i++) {
            if (i > farthest) return -1;
            farthest = Math.max(farthest, i + globalMax[i]);
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
                if (currentEnd >= n) break;
            }
        }
        return farthest < n ? -1 : jumps;
    }

    private boolean equalSubstr(long[] targetHash, long[] targetPow, int l, int r, long[] wordHash, int len) {
        long hashTarget = targetHash[r] - targetHash[l] * targetPow[r - l];
        long hashWord = wordHash[len];
        return hashTarget == hashWord;
    }
}
