import java.io.*;
import java.lang.*;
import java.util.*;

// 2506. Count Pairs Of Similar Strings

public class P2506 {
    public int similarPairs(String[] words) {
        int n = words.length;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int[] cnt1 = new int[26];
            for (int k = 0; k < words[i].length(); k++) {
                cnt1[words[i].charAt(k) - 'a']++;
            }
            for (int j = i + 1; j < n; j++) {
                int[] cnt2 = new int[26];
                for (int k = 0; k < words[j].length(); k++) {
                    cnt2[words[j].charAt(k) - 'a']++;
                }
                boolean flag = true;
                for (int k = 0; k < 26; k++) {
                    if (cnt1[k] > 0 && cnt2[k] == 0) {
                        flag = false;
                        break;
                    }
                    if (cnt1[k] == 0 && cnt2[k] > 0) {
                        flag = false;
                        break;
                    }
                }
                if (flag) ret++;
            }
        }
        return ret;
    }
}
