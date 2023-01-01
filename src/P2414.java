import java.io.*;
import java.lang.*;
import java.util.*;

// 2414. Length of the Longest Alphabetical Continuous Substring

public class P2414 {
    public int longestContinuousSubstring(String s) {
        int n = s.length();
        int ret = 1;
        char pre = s.charAt(0);
        int cnt = 1;
        for (int i = 1; i < n; i++) {
            char cur = s.charAt(i);
            if (cur - pre == 1) {
                cnt++;
            } else {
                cnt = 1;
            }
            pre = cur;
            ret = Math.max(ret, cnt);
        }
        return ret;
    }
}
