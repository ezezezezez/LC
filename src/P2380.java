import java.io.*;
import java.lang.*;
import java.util.*;

// 2380. Time Needed to Rearrange a Binary String

public class P2380 {
    public int secondsToRemoveOccurrences(String s) {
        int n = s.length();
        int ret = 0;
        while (true) {
            boolean flag = false;
            StringBuilder sb = new StringBuilder(s);
            for (int i = 1; i < n; i++) {
                if (sb.charAt(i - 1) == '0' && sb.charAt(i) == '1') {
                    sb.setCharAt(i - 1, '1');
                    sb.setCharAt(i, '0');
                    i++;
                    flag = true;
                }
            }
            if (!flag) break;
            ret++;
            s = sb.toString();
        }
        return ret;
    }

    public int secondsToRemoveOccurrences2(String s) {
        int ans = 0;
        int zeros = 0;
        for (final char c : s.toCharArray()) {
            if (c == '0') {
                ++zeros;
            }  else if (zeros > 0) { // c == '1'
                ans = Math.max(ans + 1, zeros);
            }
        }
        return ans;
    }
}
