import java.util.Arrays;

// 3301. Maximize the Total Height of Unique Towers
public class P3301 {
    public long maximumTotalSum(int[] mh) {
        int n = mh.length;
        long ret = 0L;
        Arrays.sort(mh);
        int last = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            int v = Math.min(last - 1, mh[i]);
            if (v == 0) return -1;
            ret += v;
            last = v;
        }
        return ret;
    }
}
