import java.util.*;
import java.io.*;
import java.lang.*;

// 1170. Compare Strings by Frequency of the Smallest Character

public class P1170 {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int n = words.length;
        int[] freq = new int[n];
        for (int i = 0; i < n; i++) {
            String word = words[i];
            int cnt = 0;
            char c = (char)('z' + 1);
            for (int j = 0; j < word.length(); j++) {
                char cur = word.charAt(j);
                if (cur < c) {
                    c = cur;
                    cnt = 1;
                } else if (cur == c) {
                    cnt++;
                }
            }
            freq[i] = cnt;
        }

        int m = queries.length;
        int[] ret = new int[m];
        for (int i = 0; i < m; i++) {
            int f = 0;
            char c = (char)('z' + 1);
            String query = queries[i];
            int cnt = 0;
            for (int j = 0; j < query.length(); j++) {
                char cur = query.charAt(j);
                if (cur < c) {
                    c = cur;
                    f = 1;
                } else if (cur == c) {
                    f++;
                }
            }
            for (int j = 0; j < n; j++) {
                if (freq[j] > f) ret[i]++;
            }
        }

        return ret;
    }
}
