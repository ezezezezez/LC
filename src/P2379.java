import java.io.*;
import java.lang.*;
import java.util.*;

// 2379. Minimum Recolors to Get K Consecutive Black Blocks

public class P2379 {
    public int minimumRecolors(String blocks, int k) {
        int n = blocks.length();
        int cnt = 0, ret = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            char c = blocks.charAt(i);
            if (c == 'B') cnt++;
            if (i >= k) {
                char pc = blocks.charAt(i - k);
                if (pc == 'B') cnt--;
            }
            if (i >= k - 1) {
                ret = Math.min(ret, k - cnt);
            }
        }
        return ret;
    }
}
