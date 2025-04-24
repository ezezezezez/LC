import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 3218. Minimum Cost for Cutting Cake I
public class P3218 {
    public int minimumCost(int m, int n, int[] hcut, int[] vcut) {
        Arrays.sort(hcut);
        Arrays.sort(vcut);
        int h = 1, v = 1;
        int p = hcut.length - 1, q = vcut.length - 1;
        int cost = 0;
        while (p >= 0 || q >= 0) {
            int pv = p >= 0 ? hcut[p] : -1;
            int qv = q >= 0 ? vcut[q] : -1;
            if (pv == -1) {
                cost += qv * v;
                h++;
                q--;
            } else if (qv == -1) {
                cost += pv * h;
                v++;
                p--;
            } else if (pv > qv) {
                cost += pv * h;
                v++;
                p--;
            } else {
                cost += qv * v;
                h++;
                q--;
            }
        }
        return cost;
    }
}
