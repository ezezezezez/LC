import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// 3084. Count Substrings Starting and Ending with Given Character
public class P3084 {
    public long countSubstrings(String s, char c) {
        int n = s.length();
        long ret = 0;
        long cnt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                cnt++;
                ret += cnt;
            }
        }
        return ret;
    }
}
