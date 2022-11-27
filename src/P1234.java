import java.util.*;
import java.io.*;
import java.lang.*;

// 1234. Replace the Substring for Balanced String

public class P1234 {
    char[] arr = new char[]{'Q', 'W', 'E', 'R'};
    int m;
    int[][] prefix;
    public int balancedString(String s) {
        int n = s.length();
        m = n / 4;
        prefix = new int[4][n + 1];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < n; j++) {
                prefix[i][j + 1] = prefix[i][j] + (s.charAt(j) == arr[i] ? 1 : 0);
            }
        }

        int lo = 0, hi = n;
        int t = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (valid(s, mid)) {
                t = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return t == -1 ? 0 : t;
    }

    boolean valid(String s, int len) {
        int n = s.length();

        int[] cnt = new int[4];
        for (int i = 0, j = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'Q') cnt[0]++;
            else if (c == 'W') cnt[1]++;
            else if (c == 'E') cnt[2]++;
            else cnt[3]++;
            if (i >= len) {
                char cj = s.charAt(j);
                if (cj == 'Q') cnt[0]--;
                else if (cj == 'W') cnt[1]--;
                else if (cj == 'E') cnt[2]--;
                else cnt[3]--;
                j++;
            }
            if (i >= len - 1) {
                boolean good = true;
                for (int k = 0; k < 4; k++) {
                    if (prefix[k][n] - prefix[k][i + 1] + prefix[k][j] > m) {
                        good = false;
                        break;
                    }
                }
                if (good) return true;
            }
        }
        return false;
    }
}
