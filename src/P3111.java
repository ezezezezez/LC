import java.util.Arrays;

// 3111. Minimum Rectangles to Cover Points
public class P3111 {
    public int minRectanglesToCoverPoints(int[][] points, int w) {
        int n = points.length;
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));
        int ret = 0;
        for (int i = 0, j = 0; i < n; i++) {
            int[] point = points[i];
            int x = point[0], y = point[1];
            int jx = points[j][0];
            if (jx < x - w) {
                ret++;
                j = i;
            }
        }
        return ret + 1;
    }
}
