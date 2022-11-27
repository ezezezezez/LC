import java.util.*;
import java.io.*;
import java.lang.*;

// 1156. Swap For Longest Repeated Character Substring

public class P1156 {
    int n;
    public int maxRepOpt1(String text) {
        n = text.length();
        int[][] prefix = new int[26][n + 1];
        for (int i = 0; i < 26; i++) {
            char c = (char)(i + 'a');
            for (int j = 0; j < n; j++) {
                prefix[i][j + 1] = prefix[i][j] + (c == text.charAt(j) ? 1 : 0);
            }
            // System.out.println(Arrays.toString(prefix[i]));
        }

        int ret = -1;
        int lo = 1, hi = n;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            // System.out.println(mid + " " + valid(prefix, mid));
            if (valid(prefix, mid)) {
                ret = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ret;
    }

    boolean valid(int[][] prefix, int len) {
        for (int i = 0; i < 26; i++) {
            char c = (char)(i + 'a');
            for (int j = len; j <= n; j++) {
                int left = j - len;
                if (prefix[i][j] - prefix[i][left] >= len) return true;
                if (prefix[i][j] - prefix[i][left] == len - 1) {
                    if (prefix[i][n] >= len) return true;
                }
            }
        }

        return false;
    }
}
