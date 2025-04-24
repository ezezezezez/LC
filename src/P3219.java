import java.util.Arrays;

// 3219. Minimum Cost for Cutting Cake II
public class P3219 {
    public long minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        Arrays.sort(horizontalCut);
        Arrays.sort(verticalCut);
        long h = 1, v = 1;
        int p = horizontalCut.length - 1, q = verticalCut.length - 1;
        long cost = 0;
        while (p >= 0 || q >= 0) {
            int pv = p >= 0 ? horizontalCut[p] : -1;
            int qv = q >= 0 ? verticalCut[q] : -1;
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
