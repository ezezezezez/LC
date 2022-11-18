import java.util.*;
import java.io.*;
import java.lang.*;

// 937. Reorder Data in Log Files

public class P939 {
    long mask = (long)(1e5);
    public int minAreaRect(int[][] points) {
        int n = points.length;
        int ret = Integer.MAX_VALUE;
        Set<Long> set = new HashSet<>();
        for (int[] point : points) set.add(encode(point[0], point[1]));

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int[] pointa = points[i], pointb = points[j];
                if (pointa[0] == pointb[0] || pointa[1] == pointb[1]) continue;

                int[] c = new int[]{pointa[0], pointb[1]};
                int[] d = new int[]{pointb[0], pointa[1]};

                if (set.contains(encode(c[0], c[1])) && set.contains(encode(d[0], d[1]))) {
                    int dx = Math.abs(pointa[0] - pointb[0]), dy = Math.abs(pointa[1] - pointb[1]);
                    ret = Math.min(ret, dx * dy);
                }
            }
        }

        return ret == Integer.MAX_VALUE ? 0 : ret;
    }

    long encode(long x, long y) {
        return x * mask + y;
    }

    int[] decode(long x) {
        return new int[]{(int)(x / mask), (int)(x % mask)};
    }
}
