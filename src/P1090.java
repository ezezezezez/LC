import java.util.*;
import java.io.*;
import java.lang.*;

// 1090. Largest Values From Labels

public class P1090 {
    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int n = values.length;
        useLimit = Math.min(useLimit, numWanted);

        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, k -> k);
        Arrays.sort(ids, (a, b) -> Integer.compare(values[b], values[a]));

        Map<Integer, Integer> pickCnt = new HashMap<>();
        int pos = 0;
        int ret = 0;
        while (numWanted-- > 0 && pos < n) {
            while (pos < n && pickCnt.getOrDefault(labels[ids[pos]], 0) >= useLimit) {
                pos++;
            }
            if (pos == n) break;
            // System.out.println(pos);
            ret += values[ids[pos]];
            pickCnt.merge(labels[ids[pos]], 1, Integer::sum);
            pos++;
        }

        return ret;
    }
}
