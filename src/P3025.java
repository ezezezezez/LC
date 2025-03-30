// 3025. Find the Number of Ways to Place People I
public class P3025 {
    public int numberOfPairs(int[][] points) {
        int n = points.length;
        int ret = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int[] a = points[i], b = points[j];
                if ((a[0] <= b[0] && a[1] >= b[1]) || (b[0] <= a[0] && b[1] >= a[1])) {
                    boolean valid = true;
                    for (int k = 0; k < n; k++) {
                        if (k == i || k == j) continue;
                        int[] c = points[k];
                        int minx = Math.min(a[0], b[0]), miny = Math.min(a[1], b[1]);
                        int maxx = Math.max(a[0], b[0]), maxy = Math.max(a[1], b[1]);
                        if (c[0] >= minx && c[0] <= maxx && c[1] >= miny && c[1] <= maxy) {
                            valid = false;
                            break;
                        }
                    }
                    if (valid) ret++;
                }
            }
        }
        return ret;
    }
}
