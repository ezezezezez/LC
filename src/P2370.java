import java.io.*;
import java.lang.*;
import java.util.*;

// 2370. Longest Ideal Subsequence

public class P2370 {
    public int longestIdealString(String s, int k) {
        int n = s.length();
        int[][] f = new int[n + 1][26];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            for (int j = 0; j < 26; j++) f[i + 1][j] = f[i][j];
            for (int j = 0; j < 26; j++) {
                if (Math.abs(c - 'a' - j) <= k) {
                    f[i + 1][c - 'a'] = Math.max(f[i + 1][c - 'a'], f[i][j] + 1);
                }
            }
        }
        int ret = 0;
        for (int i = 0; i < 26; i++) {
            ret = Math.max(ret, f[n][i]);
        }
        return ret;
    }
}
