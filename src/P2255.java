import java.io.*;
import java.lang.*;
import java.util.*;

// 2255. Count Prefixes of a Given String

public class P2255 {
    public int countPrefixes(String[] words, String s) {
        int n = words.length;
        int sLen = s.length();
        int ret = 0;
        for (String word : words) {
            int len = word.length();
            if (len > sLen) continue;
            boolean flag = true;
            for (int i = 0; i < len; i++) {
                if (word.charAt(i) != s.charAt(i)) {
                    flag = false;
                    break;
                }
            }
            if (flag) ret++;
        }
        return ret;
    }
}
