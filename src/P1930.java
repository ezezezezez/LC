import java.io.*;
import java.lang.*;
import java.util.*;

// 1930. Unique Length-3 Palindromic Subsequences

public class P1930 {
    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        int[] left = new int[26], right = new int[26];
        Arrays.fill(left, n);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            left[c - 'a'] = Math.min(left[c - 'a'], i);
            right[c - 'a'] = Math.max(right[c - 'a'], i);
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < n - 1; i++) {
            char c = s.charAt(i);
            for (int j = 0; j < 26; j++) {
                if (left[j] < i && right[j] > i) {
                    set.add(j * 10000 + c * 100 + j);
                }
            }
        }
        return set.size();
    }
}
