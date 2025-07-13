import java.util.*;

// 3598. Longest Common Prefix Between Adjacent Strings After Removals

public class P3598_2 {
    public int[] longestCommonPrefix(String[] words) {
        int n = words.length;
        int[] ret = new int[n];
        if (n <= 2) return ret;
        int[] sufMax = new int[n];
        for (int i = n - 2; i >= 1; i--) {
            sufMax[i] = Math.max(sufMax[i + 1], lcp(words[i], words[i + 1]));
        }
        ret[0] = sufMax[1];
        int preMax = 0;
        for (int i = 1; i < n - 1; i++) {
            ret[i] = Math.max(sufMax[i + 1], Math.max(preMax, lcp(words[i - 1], words[i + 1])));
            preMax = Math.max(preMax, lcp(words[i - 1], words[i]));
        }
        ret[n - 1] = preMax;
        return ret;
    }

    int lcp(String a, String b) {
        int i = 0;
        while (i < a.length() && i < b.length() && a.charAt(i) == b.charAt(i)) {
            i++;
        }
        return i;
    }
}
