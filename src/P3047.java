import java.util.*;

// 3047. Find the Largest Area of Square Inside Two Rectangles
public class P3047 {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        long ret = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x1 = bottomLeft[i][0], y1 = bottomLeft[i][1];
                int x2 = topRight[i][0], y2 = topRight[i][1];
                int x3 = bottomLeft[j][0], y3 = bottomLeft[j][1];
                int x4 = topRight[j][0], y4 = topRight[j][1];
                if (x3 >= x2 || x1 >= x4) continue;
                if (y3 >= y2 || y1 >= y4) continue;
                int xlen = Math.min(x2 - Math.max(x1, x3), x4 - Math.max(x3, x1));
                int ylen = Math.min(y2 - Math.max(y1, y3), y4 - Math.max(y3, y1));
                int minlen = Math.min(xlen, ylen);
                ret = Math.max(ret, 1L * minlen * minlen);
            }
        }
        return ret;
    }
}
