import java.io.*;
import java.lang.*;
import java.util.*;

// 2405. Optimal Partition of String

public class P2405 {
    public int partitionString(String s) {
        int n = s.length();
        int ret = 1;
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (cnt[c - 'a'] > 0) {
                ret++;
                Arrays.fill(cnt, 0);
            }
            cnt[c - 'a']++;
        }
        return ret;
    }
}
