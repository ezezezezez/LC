import java.io.*;
import java.lang.*;
import java.util.*;

// 2287. Rearrange Characters to Make Target String

public class P2287 {
    public int rearrangeCharacters(String s, String target) {
        int n = s.length();
        int m = target.length();
        int[] cnt = new int[26];
        int[] tCnt = new int[26];
        for (int i = 0; i < n; i++) cnt[s.charAt(i) - 'a']++;
        for (int i = 0; i < m; i++) tCnt[target.charAt(i) - 'a']++;
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (tCnt[i] > 0) {
                ret = Math.min(ret, cnt[i] / tCnt[i]);
            }
        }
        return ret;
    }
}
