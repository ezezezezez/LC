import java.io.*;
import java.lang.*;
import java.util.*;

// 1525. Number of Good Ways to Split a String

public class P1525 {
    public int numSplits(String s) {
        int n = s.length();
        int[] leftCnt = new int[26], rightCnt = new int[26];
        for (int i = 0; i < n; i++) {
            leftCnt[s.charAt(i) - 'a']++;
        }
        int ret = 0;
        for (int i = n - 1; i >= 1; i--) {
            rightCnt[s.charAt(i) - 'a']++;
            leftCnt[s.charAt(i) - 'a']--;
            int left = 0, right = 0;
            for (int j = 0; j < 26; j++) {
                if (leftCnt[j] != 0) left++;
                if (rightCnt[j] != 0) right++;
            }
            if (left == right) ret++;
        }
        return ret;
    }
}
