import java.io.*;
import java.lang.*;
import java.util.*;

// 1647. Minimum Deletions to Make Character Frequencies Unique

public class P1647 {
    public int minDeletions(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        Integer[] ids = new Integer[26];
        Arrays.setAll(ids, k -> k);
        Arrays.sort(ids, (a, b) -> {
            if (cnt[a] == 0) return 1;
            if (cnt[b] == 0) return -1;
            return Integer.compare(cnt[a], cnt[b]);
        });
        // System.out.println(Arrays.toString(ids));
        // System.out.println(Arrays.toString(cnt));
        int idx = 0;
        int ret = 0;
        while (idx < 26 && cnt[ids[idx]] != 0) idx++;
        for (int i = 0; i < idx; i++) {
            if (i > 0 && cnt[ids[i]] == cnt[ids[i - 1]]) {
                int j = i - 1;
                // System.out.println(j + " " + i);
                while (j >= 0 && cnt[ids[j]] >= cnt[ids[j + 1]]) {
                    if (cnt[ids[j + 1]] == 0) {
                        ret += cnt[ids[j]];
                        cnt[ids[j]] = 0;
                    } else {
                        ret += cnt[ids[j]] - (cnt[ids[j + 1]] - 1);
                        cnt[ids[j]] = cnt[ids[j + 1]] - 1;
                    }
                    j--;
                }
            }
        }
        return ret;
    }
}
