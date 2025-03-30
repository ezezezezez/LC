import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 2975. Maximum Square Area by Removing Fences From a Field
public class P2975 {
    long mod = (long) (1e9 + 7);
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        int hLen = hFences.length, vLen = vFences.length;
        long[] vv = new long[vLen + 2], hh = new long[hLen + 2];
        vv[0] = 1L;
        for (int i = 0; i < vLen; i++) vv[i + 1] = vFences[i];
        vv[vLen + 1] = n;
        Arrays.sort(vv);
        hh[0] = 1L;
        for (int i = 0; i < hLen; i++) hh[i + 1] = hFences[i];
        hh[hLen + 1] = m;
        Set<Long> hGaps = new HashSet<>();
        for (int i = 0; i < hLen + 2; i++) {
            for (int j = 0; j < hLen + 2; j++) {
                if (i == j) continue;
                hGaps.add(Math.abs(hh[i] - hh[j]));
            }
        }
        long ret = 0;
        for (int i = 1; i < vv.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (hGaps.contains(vv[i] - vv[j])) {
                    long d = vv[i] - vv[j];
                    ret = Math.max(ret, d * d);
                }
            }
        }
        return ret == 0L ? -1 : (int) (ret % mod);
    }
}
