import java.util.*;
import java.io.*;
import java.lang.*;

// 963. Minimum Area Rectangle II

public class P963 {
    public double minAreaFreeRect(int[][] points) {
        int n = points.length;
        double ret = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i) continue;
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) continue;
                    for (int l = 0; l < n; l++) {
                        if (l == i || l == j || l == k) continue;
                        int[] a = points[i], b = points[j], c = points[k], d = points[l];
                        double midx1 = (a[0] + c[0]) / 2.0, midy1 = (a[1] + c[1]) / 2.0;
                        double midx2 = (b[0] + d[0]) / 2.0, midy2 = (b[1] + d[1]) / 2.0;
                        int len1 = (a[0] - c[0]) * (a[0] - c[0]) + (a[1] - c[1]) * (a[1] - c[1]);
                        int len2 = (b[0] - d[0]) * (b[0] - d[0]) + (b[1] - d[1]) * (b[1] - d[1]);
                        if (midx1 == midx2 && midy1 == midy2 && len1 == len2) {
                            double lenab = Math.sqrt((a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]));
                            double lenbc = Math.sqrt((b[0] - c[0]) * (b[0] - c[0]) + (b[1] - c[1]) * (b[1] - c[1]));
                            ret = Math.min(ret, lenab * lenbc);
                        }
                    }
                }
            }
        }

        return ret == Integer.MAX_VALUE ? 0 : ret;
    }
}
