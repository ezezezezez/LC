import java.util.*;
import java.io.*;
import java.lang.*;

// 955. Delete Columns to Make Sorted II

public class P955 {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int len = strs[0].length();
        Set<Integer> set = new HashSet<>();

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < len; j++) {
                if (set.contains(j) || strs[i].charAt(j) == strs[i - 1].charAt(j)) continue;
                if (strs[i].charAt(j) < strs[i - 1].charAt(j)) {
                    set.add(j);
                    i = 0;
                }
                break;
            }
        }

        return set.size();
    }
}
