import java.io.*;
import java.lang.*;
import java.util.*;

// 2423. Remove Letter To Equalize Frequency

public class P2423 {
    public boolean equalFrequency(String word) {
        int n = word.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[word.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            cnt[c - 'a']--;
            boolean flag = true;
            int pre = -1;
            for (int j = 0; j < 26; j++) {
                if (pre == -1 && cnt[j] > 0) pre = cnt[j];
                if (cnt[j] > 0 && cnt[j] != pre) {
                    flag = false;
                    break;
                }
            }
            if (flag) return true;
            cnt[c - 'a']++;
        }
        return false;
    }
}
