import java.util.*;
import java.io.*;
import java.lang.*;

// 1177. Can Make Palindrome from Substring

public class P1177 {
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int m = queries.length;
        List<Boolean> ret = new ArrayList<>();
        int n = s.length();
        int[][] prefix = new int[26][n + 1];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < n; j++) {
                prefix[i][j + 1] = prefix[i][j] + (s.charAt(j) - 'a' == i ? 1 : 0);
            }
        }
        for (int[] query : queries) {
            int left = query[0], right = query[1], k = query[2];
            int len = right - left + 1;
            int cnt = 0;
            for (int i = 0; i < 26; i++) {
                if ((prefix[i][right + 1] - prefix[i][left]) % 2 == 1) cnt++;
            }
            if (len % 2 == 1) cnt--;
            ret.add((cnt + 1) / 2 <= k);
        }

        return ret;
    }
}
