import java.io.*;
import java.lang.*;
import java.util.*;

// 2273. Find Resultant Array After Removing Anagrams

public class P2273 {
    public List<String> removeAnagrams(String[] words) {
        int n = words.length;
        List<String> ret = new ArrayList<>();
        int[][] arr = new int[n][26];
        for (int i = 0; i < n; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                arr[i][word.charAt(j) - 'a']++;
            }
        }
        ret.add(words[0]);
        int pre = 0;
        for (int i = 1; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < 26; j++) {
                if (arr[pre][j] != arr[i][j]) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                ret.add(words[i]);
                pre = i;
            }
        }
        return ret;
    }
}
