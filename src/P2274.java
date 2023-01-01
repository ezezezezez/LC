import java.io.*;
import java.lang.*;
import java.util.*;

// 2274. Maximum Consecutive Floors Without Special Floors

public class P2274 {
    public int maxConsecutive(int bottom, int top, int[] special) {
        int n = special.length;
        Arrays.sort(special);
        int ret = special[0] - bottom;
        int pre = special[0];
        for (int i = 1; i < n; i++) {
            ret = Math.max(ret, special[i] - pre - 1);
            pre = special[i];
        }
        ret = Math.max(ret, top - special[n - 1]);
        return ret;
    }
}
