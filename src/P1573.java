import java.io.*;
import java.lang.*;
import java.util.*;

// 1573. Number of Ways to Split a String

public class P1573 {
    public int numWays(String s) {
        int n = s.length();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') cnt++;
        }
        if (cnt % 3 != 0) return 0;
        // System.out.println(s);
        int ret = 0;
        int mod = (int)(1e9 + 7);
        if (cnt == 0) {
            for (int i = 0; i <= n - 3; i++) {
                int m = n - 2 - i;
                ret = (ret + m) % mod;
            }
            return ret;
        }
        int singleCnt = cnt / 3;
        int left = 0, right = n - 1;
        int leftCnt = 0, rightCnt = 0;
        while (leftCnt < singleCnt) {
            if (s.charAt(left) == '1') leftCnt++;
            left++;
        }
        while (rightCnt < singleCnt) {
            if (s.charAt(right) == '1') rightCnt++;
            right--;
        }
        int leftOld = left - 1;
        while (s.charAt(left) != '1') left++;
        int rightOld = right + 1;
        while (s.charAt(right) != '1') right--;
        ret = (int)(1L * (left - leftOld) * (rightOld - right) % mod);
        return ret;
    }
}
