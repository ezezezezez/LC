import java.io.*;
import java.lang.*;
import java.util.*;

// 1653. Minimum Deletions to Make String Balanced

public class P1653 {
    public int minimumDeletions(String s) {
        int n = s.length();
        int[] right = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            right[i] = right[i + 1] + (s.charAt(i) == 'a' ? 1 : 0);
        }
        int ret = right[0];
        int bCnt = 0;
        for (int i = 0; i < n; i++) {
            bCnt += s.charAt(i) == 'b' ? 1 : 0;
            ret = Math.min(ret, bCnt + right[i + 1]);
        }
        return ret;
    }
}
