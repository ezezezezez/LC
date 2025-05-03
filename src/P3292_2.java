// 3292. Minimum Number of Valid Strings to Form Target II
public class P3292_2 {
    public int minValidStrings(String[] words, String target) {
        int n = target.length();
        int[] globalMax = new int[n];

        for (String word : words) {
            int m = word.length();
            if (target.indexOf(word.charAt(0)) == -1) continue;

            String combined = word + "$" + target;
            int[] pi = computePrefixFunction(combined);
            int combinedLen = combined.length();
            int[] local = new int[n];

            for (int pos = m + 1; pos < combinedLen; pos++) {
                int j = pos - (m + 1);
                int cand = pi[pos];
                if (cand > 0) {
                    int start = j - cand + 1;
                    globalMax[start] = Math.max(globalMax[start], cand);
                }
            }
        }

        int jumps = 0, currentEnd = 0, farthest = 0;
        for (int i = 0; i < n; i++) {
            if (i > farthest) {
                return -1;
            }
            farthest = Math.max(farthest, i + globalMax[i]);
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
                if (currentEnd >= n) {
                    break;
                }
            }
        }
        return farthest < n ? -1 : jumps;
    }

    // Standard KMP prefix function.
    // For each index i in string s, pi[i] is the length of the longest proper (not entire string) prefix
    // of s[0...i] that is also a suffix of s[0...i].
    private int[] computePrefixFunction(String s) {
        int n = s.length();
        int[] pi = new int[n];
        int k = 0;
        for (int i = 1; i < n; i++) {
            while (k > 0 && s.charAt(i) != s.charAt(k)) {
                k = pi[k - 1];
            }
            if (s.charAt(i) == s.charAt(k)) {
                k++;
            }
            pi[i] = k;
        }
        return pi;
    }
}
