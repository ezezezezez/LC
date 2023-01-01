import java.io.*;
import java.lang.*;
import java.util.*;

// 2310. Sum of Numbers With Units Digit K
// notes: read the question carefully

public class P2311 {
    public int longestSubsequence(String s, int k) {
        int n = s.length();
        int oneCnt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') oneCnt++;
        }
        int zeroCnt = n - oneCnt;
        int ret = 0;
        for (int i = 0; i <= oneCnt; i++) {
            int sum = 0, mult = 1;
            int t = i;
            int tz = 0;
            int j = n - 1;
            for (; j >= 0; j--) {
                char c = s.charAt(j);
                if (c == '0') {
                    tz++;
                } else {
                    if (t == 0) break;
                    if (sum + mult > k) break;
                    t--;
                    sum += mult;
                }
                mult = Math.min(mult * 2, 0x3f3f3f3f);
            }
            // System.out.println(i + " " + j + " " + (n - j - 1 + zeroCnt - tz));
            ret = Math.max(ret, n - j - 1 + zeroCnt - tz);
        }
        return ret;
    }

    public int longestSubsequence2(String s, int k) {
        int n = s.length();
        int ret = 0, sum = 0;
        int mult = 1;
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '0') {
                ret++;
            } else {
                if (sum + mult <= k) {
                    ret++;
                    sum += mult;
                }
            }
            mult = Math.min(2 * mult, 0x3f3f3f3f);
        }
        return ret;
    }
}
