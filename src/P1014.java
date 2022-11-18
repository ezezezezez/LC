import java.util.*;
import java.io.*;
import java.lang.*;

// 1014. Best Sightseeing Pair

public class P1014 {
    public int maxScoreSightseeingPair(int[] values) {
        int n = values.length;
        int ret = 0, pre = values[0];

        for (int i = 1; i < n; i++) {
            ret = Math.max(ret, pre + values[i] - i);
            pre = Math.max(pre, values[i] + i);
        }

        return ret;
    }
}
