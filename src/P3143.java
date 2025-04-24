import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 3143. Maximum Points Inside the Square
public class P3143 {
    public int maxPointsInsideSquare(int[][] points, String s) {
        int n = points.length;
        int ret = 0;
        int lo = 0, hi = Integer.MAX_VALUE / 2;
        Set<Character> tags = new HashSet<>();
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int l = -mid, r = mid, t = mid, b = -mid;
            boolean valid = true;
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                int[] point = points[i];
                int x = point[0], y = point[1];
                if (l <= x && x <= r && b <= y && y <= t) {
                    cnt++;
                    if (tags.contains(s.charAt(i))) {
                        valid = false;
                        break;
                    } else {
                        tags.add(s.charAt(i));
                    }
                }
            }
            if (valid) {
                ret = cnt;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
            tags.clear();
        }
        return ret;
    }
}
